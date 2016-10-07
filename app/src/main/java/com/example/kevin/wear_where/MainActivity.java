
package com.example.kevin.wear_where;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.wear_where.data.Channel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener {

    private static final String TAG = "MainActivity";

    double latitude = 0;                                    // Variables used to keep track of the current latitude and longitude
    double longitude = 0;

    double startingLatitude = 0;
    double startingLongitude = 0;
    double endingLatitude = 0;
    double endingLongitude = 0;

    MapFragment mapFragment;                                /* Variable used to create an instance of the mapFragment
                                                               --> Call getMapAsync(this) on mapFragment to update the map */

    GoogleApiClient mGoogleApiClient;                       // Variable used to create an instance of the GoogleApiClient
    Location mLastLocation;                                 // Variable used to store the most recent location
    TextView temperature, location, description;            // TextView in xml.
    URL request;                                            // The link requesting service from Yahoo query
    Channel channel;                                        // Channel Object

    String city = "Buffalo";
    String state = "NY";

    /* Called BEFORE the activity is visible! i.e. do anything that needs to be done before the application is visible.
       Also called whenever the application is launched and there are no existing resources to work with (i.e. first start or application was killed before) */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the tab host
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //Create tab1
        TabHost.TabSpec homeTab = tabHost.newTabSpec("homeTab");
        homeTab.setIndicator("Weather");
        homeTab.setContent(R.id.layout1);
        tabHost.addTab(homeTab);

        //Create tab2
        homeTab = tabHost.newTabSpec("homeTab2");
        homeTab.setIndicator("Apparel");
        homeTab.setContent(R.id.layout2);
        tabHost.addTab(homeTab);

        //Create tab3
        homeTab = tabHost.newTabSpec("homeTab3");
        homeTab.setIndicator("Vacation?");
        homeTab.setContent(R.id.layout3);
        tabHost.addTab(homeTab);

        //Create tab4
        homeTab = tabHost.newTabSpec("homeTab4");
        homeTab.setIndicator("Road\nTrip!");
        homeTab.setContent(R.id.layout4);
        tabHost.addTab(homeTab);

        //Get a reference to the google maps fragment in tab4
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
    }

    @Override   //Called when the activity becomes visible to the user. Also called when coming back to the application from another application (assuming this application wasn't killed).
    protected void onStart() {

        //Build and instantiate an instance of the Google API Client
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        }

        //Attempt to connect to Google Play Services
        if(mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }

        else {
            TextView currentLocation = (TextView) findViewById(R.id.locationCoordinates);
            currentLocation.setText("Could not connect to Google Play Services!");
        }

        temperature = (TextView) findViewById(R.id.temperature);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.description);

        channel = new Channel();
        getRequest();

        //Set up PlaceAutocompleteFragments and their listeners
        PlaceAutocompleteFragment startingLocation = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.starting_location_autocomplete_fragment);
        PlaceAutocompleteFragment endingLocation = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.ending_location_autocomplete_fragment);

        startingLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                startingLatitude = place.getLatLng().latitude;
                startingLongitude = place.getLatLng().longitude;
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        endingLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                endingLatitude = place.getLatLng().latitude;
                endingLongitude = place.getLatLng().longitude;
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        super.onStart();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        int MY_PERMISSION_ACCESS_COARSE_LOCATION = 77;
        int MY_PERMISSION_ACCESS_FINE_LOCATION = 77;

        //request permission for coarse location if not granted already
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION );
        }

        //request permission for fine location if not granted already
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_ACCESS_FINE_LOCATION );
        }

        //If the starting and ending points have been initialized then set the markers on the map(need to figure out a better way to check this...)
        if (startingLatitude != 0 && startingLongitude != 0) {
            map.addMarker(new MarkerOptions().position(new LatLng(startingLatitude, startingLongitude)));
        }

        if (endingLatitude != 0 && endingLongitude != 0) {
            map.addMarker(new MarkerOptions().position(new LatLng(endingLatitude, endingLongitude)));
        }

        //Move the camera to the current location (used upon start up, may encounter bugs later on when calling getMapAsync() after startup)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
        map.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(Bundle arg0) {
        getLocation(null);
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        TextView currentLocation = (TextView) findViewById(R.id.locationCoordinates);
        currentLocation.setText("Connection Suspended!");
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        TextView currentLocation = (TextView) findViewById(R.id.locationCoordinates);
        currentLocation.setText("Connection Failed!");
    }

    private void displayResults() {
        temperature.setText("" + channel.getTemp() + (char) 0x00B0 + " F");
        location.setText("Buffalo, NY");
        description.setText(channel.getCondition());
        // Debugger
        //Log.d("WearWhere", "" + channel.getItem().getCondition().getTemperature());
        //temperature.setText("" + channel.getLocation());
    }

    public void getRequest() {
        new AsyncTask<Void, Channel, Channel>() {

            @Override
            protected Channel doInBackground(Void... params) {
                // Temporary Channel Object holder
                Channel channelTemp = new Channel();

                String link = String.format("http://api.wunderground.com/api/ca5b9df3415b7849/hourly/q/%s/%s.json", Uri.encode(state), Uri.encode(city));

                try {
                    request = new URL(link);
                    // Open a URL connection to link
                    URLConnection urlConnection = request.openConnection();
                    // Get the input stream of link
                    InputStream in = urlConnection.getInputStream();
                    // Read buffer
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    // Store the buffer of link into result
                    StringBuilder result = new StringBuilder();
                    // Store each line of buffer into line
                    String line;

                    // Get each line from buffer and stores them into result
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    /*JSONObject data = new JSONObject(result.toString());
                    JSONObject queryResult = data.optJSONObject("query");
                    // Check for number of results to verify acceptable location
                    int count = queryResult.optInt("count");
                    if (count == 0) {
                        return null;
                    }
                    JSONObject resultsObject = queryResult.optJSONObject("results");
                    JSONObject channelObject = resultsObject.optJSONObject("channel");
                    channelTemp.retrieveData(channelObject);*/

                    JSONObject jsonRootObject = new JSONObject(result.toString());
                    JSONArray jsonArray = jsonRootObject.optJSONArray("hourly_forecast");
                    JSONObject FCTTIME = jsonArray.getJSONObject(0);
                    JSONObject temp = FCTTIME.getJSONObject("temp");
                    String temp_english = temp.optString("english").toString();
                    String condition = FCTTIME.optString("condition").toString();

                    channelTemp.setTemp(temp_english);
                    channelTemp.setCondition(condition);

                    return channelTemp;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Channel item) {
                channel = item;
                displayResults();
            }
        }.execute();
    }

    //use this to get the current location (just pass NULL in here)
    public void getLocation(View view) {
        int MY_PERMISSION_ACCESS_COARSE_LOCATION = 77;
        int MY_PERMISSION_ACCESS_FINE_LOCATION = 77;

        //request permission for coarse location if not granted already
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION );
        }

        //request permission for fine location if not granted already
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_ACCESS_FINE_LOCATION );
        }

        //get the last location from the GoogleApiClient
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            TextView currentLocation = (TextView) findViewById(R.id.locationCoordinates);
            currentLocation.setText("Current Location: Latitude = " + String.valueOf(mLastLocation.getLatitude()) + ", Longitude = " + String.valueOf(mLastLocation.getLongitude()));
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                location.setText(city + ", " + state);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //update the map with the zoom of the current location
        mapFragment.getMapAsync(this);
    }

    public void placeMarkers(View view) {
        //update the map with the corresponding markers for the starting and ending points
        mapFragment.getMapAsync(this);
    }
}