package com.example.kevin.wear_where;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kevin.wear_where.AsyncTask.DailyForecastAST;
import com.example.kevin.wear_where.AsyncTask.GoogleDirectionsAST;
import com.example.kevin.wear_where.AsyncTask.IntervalInformationAST;
import com.example.kevin.wear_where.Google.Directions.DirectionsObject;
import com.example.kevin.wear_where.WundergroundData.DailyForecast.DailyObject;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.example.kevin.wear_where.AsyncTask.CurrentConditionAST;
import com.example.kevin.wear_where.AsyncTask.HourlyForecastAST;
import com.example.kevin.wear_where.AsyncTask.ImageLoaderAST;
import com.example.kevin.wear_where.WundergroundData.CurrentCondition.ConditionsObject;

import com.example.kevin.wear_where.WundergroundData.HourlyForecast.HourlyObject;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener {

    // Variable used to keep track of whether or not this is the initial start up of the application
    private int firstStart = 1;

    // Variable used to store an instance of the map in the road trip tab
    private GoogleMap maps;

    // Variable used to create an instance of the mapFragment (Call getMapAsync(this) on mapFragment to update the map)
    private MapFragment mapFragment;

    // Variable used to create an instance of the GoogleApiClient
    private GoogleApiClient mGoogleApiClient;

    // Variable used to keep track of your most recent location
    private Location mLastLocation;

    // Variables used to keep track of the latitude and longitude of your current location;
    private double latitude = 0;
    private double longitude = 0;

    // Variables used to create instances of the places fragments in the vacation tab and road trip tab
    private PlaceAutocompleteFragment vacationLocation;
    private PlaceAutocompleteFragment startingLocation;
    private PlaceAutocompleteFragment endingLocation;

    // Latitude and Longitude doubles initialized to 900 since this value is impossible
    private double vacationLatitude = 900;
    private double vacationLongitude = 900;

    private LatLng startingCoordinates = null;
    private LatLng endingCoordinates = null;

    // Variable that stores a parsed JSON object from the Google Directions API Query Result
    private DirectionsObject directionsObject;

    // Variable used to store the interval points pending weather info
    private ArrayList<LatLng> weatherPoints;

    // Variable used to store the markers that will be placed on the map
    private ArrayList<MarkerOptions> markers;

    // Variable used to store multiple points along the route between the starting and ending locations input in the road trip tab (used to draw the polyline on the map between the two points)
    private List<LatLng> polyline;

    private TextView temperature, location, description;            // TextView for current weather information
    private ImageView conditionIcon;                                // ImageView for current condition image
    private ConditionsObject currentForecast;                       // Object holding the current weather information
    private HourlyObject hourlyForecast;                            // Object holding the hourly weather information
    private DailyObject dailyForecast;                              // Object holding the daily weather information

    // Hourly forecast widgets
    private TextView temperature1, description1, time1;
    private ImageView icon1;
    private TextView temperature2, description2, time2;
    private ImageView icon2;
    private TextView temperature3, description3, time3;
    private ImageView icon3;
    private TextView temperature4, description4, time4;
    private ImageView icon4;
    private TextView temperature5, description5, time5;
    private ImageView icon5;
    private TextView temperature6, description6, time6;
    private ImageView icon6;
    private TextView temperature7, description7, time7;
    private ImageView icon7;
    private TextView temperature8, description8, time8;
    private ImageView icon8;
    private TextView temperature9, description9, time9;
    private ImageView icon9;
    private TextView temperature10, description10, time10;
    private ImageView icon10;
    private TextView temperature11, description11, time11;
    private ImageView icon11;
    private TextView temperature12, description12, time12;
    private ImageView icon12;
    private TextView temperature13, description13, time13;
    private ImageView icon13;
    private TextView temperature14, description14, time14;
    private ImageView icon14;
    private TextView temperature15, description15, time15;
    private ImageView icon15;
    private TextView temperature16, description16, time16;
    private ImageView icon16;

    // Daily forecast widgets
    private ImageView day1icon;
    private TextView day1weekday, day1condition, day1low, day1high;
    private ImageView day2icon;
    private TextView day2weekday, day2condition, day2low, day2high;
    private ImageView day3icon;
    private TextView day3weekday, day3condition, day3low, day3high;
    private ImageView day4icon;
    private TextView day4weekday, day4condition, day4low, day4high;
    private ImageView day5icon;
    private TextView day5weekday, day5condition, day5low, day5high;
    private ImageView day6icon;
    private TextView day6weekday, day6condition, day6low, day6high;
    private ImageView day7icon;
    private TextView day7weekday, day7condition, day7low, day7high;

    private String city;
    private String state;

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
        super.onStart();

        //Build and instantiate an instance of the Google API Client
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        }

        //Attempt to connect to Google Play Services
        if(mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }

        //Set up PlaceAutocompleteFragments and their listeners
        vacationLocation = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.vacation_location_autocomplete_fragment);
        vacationLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // Listener for the places fragment in the vacation tab (get whatever is needed from the places argument)
                vacationLatitude = place.getLatLng().latitude;
                vacationLongitude = place.getLatLng().longitude;
            }

            @Override
            public void onError(Status status) {
                // TODO: DO STUFF ON ERROR (Toast?)
            }
        });

        startingLocation = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.starting_location_autocomplete_fragment);
        startingLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // Listener for the places fragment pertaining to the starting location in the road trip tab (get whatever is needed from the places argument)
                startingCoordinates = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {
                // TODO: DO STUFF ON ERROR (Toast?)
            }
        });

        endingLocation = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.ending_location_autocomplete_fragment);
        endingLocation.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // Listener for the places fragment pertaining to the ending location in the road trip tab (get whatever is needed from the places argument)
                endingCoordinates = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {
                // TODO: DO STUFF ON ERROR (Toast?)
            }
        });

        // Assign hourly forecast widgets
        temperature = (TextView) findViewById(R.id.temperature);
        conditionIcon = (ImageView) findViewById(R.id.conditionIcon);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.description);

        temperature1 = (TextView) findViewById(R.id.temperature1);
        description1 = (TextView) findViewById(R.id.description1);
        time1 = (TextView) findViewById(R.id.time1);
        icon1 = (ImageView) findViewById(R.id.icon1);

        temperature2 = (TextView) findViewById(R.id.temperature2);
        description2 = (TextView) findViewById(R.id.description2);
        time2 = (TextView) findViewById(R.id.time2);
        icon2 = (ImageView) findViewById(R.id.icon2);

        temperature3 = (TextView) findViewById(R.id.temperature3);
        description3 = (TextView) findViewById(R.id.description3);
        time3 = (TextView) findViewById(R.id.time3);
        icon3 = (ImageView) findViewById(R.id.icon3);

        temperature4 = (TextView) findViewById(R.id.temperature4);
        description4 = (TextView) findViewById(R.id.description4);
        time4 = (TextView) findViewById(R.id.time4);
        icon4 = (ImageView) findViewById(R.id.icon4);

        temperature5 = (TextView) findViewById(R.id.temperature5);
        description5 = (TextView) findViewById(R.id.description5);
        time5 = (TextView) findViewById(R.id.time5);
        icon5 = (ImageView) findViewById(R.id.icon5);

        temperature6 = (TextView) findViewById(R.id.temperature6);
        description6 = (TextView) findViewById(R.id.description6);
        time6 = (TextView) findViewById(R.id.time6);
        icon6 = (ImageView) findViewById(R.id.icon6);

        temperature7 = (TextView) findViewById(R.id.temperature7);
        description7 = (TextView) findViewById(R.id.description7);
        time7 = (TextView) findViewById(R.id.time7);
        icon7 = (ImageView) findViewById(R.id.icon7);

        temperature8 = (TextView) findViewById(R.id.temperature8);
        description8 = (TextView) findViewById(R.id.description8);
        time8 = (TextView) findViewById(R.id.time8);
        icon8 = (ImageView) findViewById(R.id.icon8);

        temperature9 = (TextView) findViewById(R.id.temperature9);
        description9 = (TextView) findViewById(R.id.description9);
        time9 = (TextView) findViewById(R.id.time9);
        icon9 = (ImageView) findViewById(R.id.icon9);

        temperature10 = (TextView) findViewById(R.id.temperature10);
        description10 = (TextView) findViewById(R.id.description10);
        time10 = (TextView) findViewById(R.id.time10);
        icon10 = (ImageView) findViewById(R.id.icon10);

        temperature11 = (TextView) findViewById(R.id.temperature11);
        description11 = (TextView) findViewById(R.id.description11);
        time11 = (TextView) findViewById(R.id.time11);
        icon11 = (ImageView) findViewById(R.id.icon11);

        temperature12 = (TextView) findViewById(R.id.temperature12);
        description12 = (TextView) findViewById(R.id.description12);
        time12 = (TextView) findViewById(R.id.time12);
        icon12 = (ImageView) findViewById(R.id.icon12);

        temperature13 = (TextView) findViewById(R.id.temperature13);
        description13 = (TextView) findViewById(R.id.description13);
        time13 = (TextView) findViewById(R.id.time13);
        icon13 = (ImageView) findViewById(R.id.icon13);

        temperature14 = (TextView) findViewById(R.id.temperature14);
        description14 = (TextView) findViewById(R.id.description14);
        time14 = (TextView) findViewById(R.id.time14);
        icon14 = (ImageView) findViewById(R.id.icon14);

        temperature15 = (TextView) findViewById(R.id.temperature15);
        description15 = (TextView) findViewById(R.id.description15);
        time15 = (TextView) findViewById(R.id.time15);
        icon15 = (ImageView) findViewById(R.id.icon15);

        temperature16 = (TextView) findViewById(R.id.temperature16);
        description16 = (TextView) findViewById(R.id.description16);
        time16 = (TextView) findViewById(R.id.time16);
        icon16 = (ImageView) findViewById(R.id.icon16);

        // Assign daily forecast widget
        day1icon = (ImageView) findViewById(R.id.day1icon);
        day1weekday = (TextView) findViewById(R.id.day1weekday);
        day1condition = (TextView) findViewById(R.id.day1condition);
        day1low = (TextView) findViewById(R.id.day1low);
        day1high = (TextView) findViewById(R.id.day1high);

        day2icon = (ImageView) findViewById(R.id.day2icon);
        day2weekday = (TextView) findViewById(R.id.day2weekday);
        day2condition = (TextView) findViewById(R.id.day2condition);
        day2low = (TextView) findViewById(R.id.day2low);
        day2high = (TextView) findViewById(R.id.day2high);

        day3icon = (ImageView) findViewById(R.id.day3icon);
        day3weekday = (TextView) findViewById(R.id.day3weekday);
        day3condition = (TextView) findViewById(R.id.day3condition);
        day3low = (TextView) findViewById(R.id.day3low);
        day3high = (TextView) findViewById(R.id.day3high);

        day4icon = (ImageView) findViewById(R.id.day4icon);
        day4weekday = (TextView) findViewById(R.id.day4weekday);
        day4condition = (TextView) findViewById(R.id.day4condition);
        day4low = (TextView) findViewById(R.id.day4low);
        day4high = (TextView) findViewById(R.id.day4high);

        day5icon = (ImageView) findViewById(R.id.day5icon);
        day5weekday = (TextView) findViewById(R.id.day5weekday);
        day5condition = (TextView) findViewById(R.id.day5condition);
        day5low = (TextView) findViewById(R.id.day5low);
        day5high = (TextView) findViewById(R.id.day5high);

        day6icon = (ImageView) findViewById(R.id.day6icon);
        day6weekday = (TextView) findViewById(R.id.day6weekday);
        day6condition = (TextView) findViewById(R.id.day6condition);
        day6low = (TextView) findViewById(R.id.day6low);
        day6high = (TextView) findViewById(R.id.day6high);

        day7icon = (ImageView) findViewById(R.id.day7icon);
        day7weekday = (TextView) findViewById(R.id.day7weekday);
        day7condition = (TextView) findViewById(R.id.day7condition);
        day7low = (TextView) findViewById(R.id.day7low);
        day7high = (TextView) findViewById(R.id.day7high);
    }


    // Executes once google play services establishes a connection
    @Override
    public void onConnected(Bundle arg0) {
        getLocation();
    }

    // Executes once the connection for google play services is suspended
    @Override
    public void onConnectionSuspended(int arg0) {

    }

    // Executes if the attempt to connect to google play services fails
    @Override
    public void onConnectionFailed(ConnectionResult arg0) {

    }

    //use this to get the current location
    public void getLocation() {
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
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                location.setText(city + ", " + state);
                getCurrentRequest();
                getHourlyRequest();
                getDailyRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //update the map with the current location
        mapFragment.getMapAsync(this);
    }

    private void displayCurrentResults() {
        String iconURL = currentForecast.getConditionImageLink();
        new ImageLoaderAST(iconURL, conditionIcon).execute();

        temperature.setText("" + currentForecast.getTemperature() + (char) 0x00B0 + " F");
        description.setText("" + currentForecast.getCondition());
        location.setText("" + city + ", " + state);
    }

    public void displayHourlyResults(){
        String iconURL = hourlyForecast.getIconURL(0);
        new ImageLoaderAST(iconURL, icon1).execute();

        iconURL = hourlyForecast.getIconURL(1);
        new ImageLoaderAST(iconURL, icon2).execute();

        iconURL = hourlyForecast.getIconURL(2);
        new ImageLoaderAST(iconURL, icon3).execute();

        iconURL = hourlyForecast.getIconURL(3);
        new ImageLoaderAST(iconURL, icon4).execute();

        iconURL = hourlyForecast.getIconURL(4);
        new ImageLoaderAST(iconURL, icon5).execute();

        iconURL = hourlyForecast.getIconURL(5);
        new ImageLoaderAST(iconURL, icon6).execute();

        iconURL = hourlyForecast.getIconURL(6);
        new ImageLoaderAST(iconURL, icon7).execute();

        iconURL = hourlyForecast.getIconURL(7);
        new ImageLoaderAST(iconURL, icon8).execute();

        iconURL = hourlyForecast.getIconURL(8);
        new ImageLoaderAST(iconURL, icon9).execute();

        iconURL = hourlyForecast.getIconURL(9);
        new ImageLoaderAST(iconURL, icon10).execute();

        iconURL = hourlyForecast.getIconURL(10);
        new ImageLoaderAST(iconURL, icon11).execute();

        iconURL = hourlyForecast.getIconURL(11);
        new ImageLoaderAST(iconURL, icon12).execute();

        iconURL = hourlyForecast.getIconURL(12);
        new ImageLoaderAST(iconURL, icon13).execute();

        iconURL = hourlyForecast.getIconURL(13);
        new ImageLoaderAST(iconURL, icon14).execute();

        iconURL = hourlyForecast.getIconURL(14);
        new ImageLoaderAST(iconURL, icon15).execute();

        iconURL = hourlyForecast.getIconURL(15);
        new ImageLoaderAST(iconURL, icon16).execute();

        temperature1.setText("" + hourlyForecast.getTemperatureF(0) + (char) 0x00B0 + " F");
        time1.setText("" + hourlyForecast.getHour(0) + ":00");
        description1.setText("" + hourlyForecast.getCondition(0));

        temperature2.setText("" + hourlyForecast.getTemperatureF(1) + (char) 0x00B0 + " F");
        time2.setText("" + hourlyForecast.getHour(1) + ":00");
        description2.setText("" + hourlyForecast.getCondition(1));

        temperature3.setText("" + hourlyForecast.getTemperatureF(2) + (char) 0x00B0 + " F");
        time3.setText("" + hourlyForecast.getHour(2) + ":00");
        description3.setText("" + hourlyForecast.getCondition(2));

        temperature4.setText("" + hourlyForecast.getTemperatureF(3) + (char) 0x00B0 + " F");
        time4.setText("" + hourlyForecast.getHour(3) + ":00");
        description4.setText("" + hourlyForecast.getCondition(3));

        temperature5.setText("" + hourlyForecast.getTemperatureF(4) + (char) 0x00B0 + " F");
        time5.setText("" + hourlyForecast.getHour(4) + ":00");
        description5.setText("" + hourlyForecast.getCondition(4));

        temperature6.setText("" + hourlyForecast.getTemperatureF(5) + (char) 0x00B0 + " F");
        time6.setText("" + hourlyForecast.getHour(5) + ":00");
        description6.setText("" + hourlyForecast.getCondition(5));

        temperature7.setText("" + hourlyForecast.getTemperatureF(6) + (char) 0x00B0 + " F");
        time7.setText("" + hourlyForecast.getHour(6) + ":00");
        description7.setText("" + hourlyForecast.getCondition(6));

        temperature8.setText("" + hourlyForecast.getTemperatureF(7) + (char) 0x00B0 + " F");
        time8.setText("" + hourlyForecast.getHour(7) + ":00");
        description8.setText("" + hourlyForecast.getCondition(7));

        temperature9.setText("" + hourlyForecast.getTemperatureF(8) + (char) 0x00B0 + " F");
        time9.setText("" + hourlyForecast.getHour(8) + ":00");
        description9.setText("" + hourlyForecast.getCondition(8));

        temperature10.setText("" + hourlyForecast.getTemperatureF(9) + (char) 0x00B0 + " F");
        time10.setText("" + hourlyForecast.getHour(9) + ":00");
        description10.setText("" + hourlyForecast.getCondition(9));

        temperature11.setText("" + hourlyForecast.getTemperatureF(10) + (char) 0x00B0 + " F");
        time11.setText("" + hourlyForecast.getHour(10) + ":00");
        description11.setText("" + hourlyForecast.getCondition(10));

        temperature12.setText("" + hourlyForecast.getTemperatureF(11) + (char) 0x00B0 + " F");
        time12.setText("" + hourlyForecast.getHour(11) + ":00");
        description12.setText("" + hourlyForecast.getCondition(11));

        temperature13.setText("" + hourlyForecast.getTemperatureF(12) + (char) 0x00B0 + " F");
        time13.setText("" + hourlyForecast.getHour(12) + ":00");
        description13.setText("" + hourlyForecast.getCondition(12));

        temperature14.setText("" + hourlyForecast.getTemperatureF(13) + (char) 0x00B0 + " F");
        time14.setText("" + hourlyForecast.getHour(13) + ":00");
        description14.setText("" + hourlyForecast.getCondition(13));

        temperature15.setText("" + hourlyForecast.getTemperatureF(14) + (char) 0x00B0 + " F");
        time15.setText("" + hourlyForecast.getHour(14) + ":00");
        description15.setText("" + hourlyForecast.getCondition(14));

        temperature16.setText("" + hourlyForecast.getTemperatureF(15) + (char) 0x00B0 + " F");
        time16.setText("" + hourlyForecast.getHour(15) + ":00");
        description16.setText("" + hourlyForecast.getCondition(15));

    }

    public void displayDailyResults(){
        String iconURL = dailyForecast.getIconURL(1);
        new ImageLoaderAST(iconURL, day1icon).execute();

        iconURL = dailyForecast.getIconURL(2);
        new ImageLoaderAST(iconURL, day2icon).execute();

        iconURL = dailyForecast.getIconURL(3);
        new ImageLoaderAST(iconURL, day3icon).execute();

        iconURL = dailyForecast.getIconURL(4);
        new ImageLoaderAST(iconURL, day4icon).execute();

        iconURL = dailyForecast.getIconURL(5);
        new ImageLoaderAST(iconURL, day5icon).execute();

        iconURL = dailyForecast.getIconURL(6);
        new ImageLoaderAST(iconURL, day6icon).execute();

        iconURL = dailyForecast.getIconURL(7);
        new ImageLoaderAST(iconURL, day7icon).execute();

        day1weekday.setText(dailyForecast.getDate(1));
        day1condition.setText(dailyForecast.getCondition(1));
        day1low.setText("Low: " + dailyForecast.getLow(1) + (char) 0x00B0 + " F");
        day1high.setText("High: " + dailyForecast.getHigh(1) + (char) 0x00B0 + " F");

        day2weekday.setText(dailyForecast.getDate(2));
        day2condition.setText(dailyForecast.getCondition(2));
        day2low.setText("Low " + dailyForecast.getLow(2) + (char) 0x00B0 + " F");
        day2high.setText("High: " + dailyForecast.getHigh(2) + (char) 0x00B0 + " F");

        day3weekday.setText(dailyForecast.getDate(3));
        day3condition.setText(dailyForecast.getCondition(3));
        day3low.setText("Low: " + dailyForecast.getLow(3) + (char) 0x00B0 + " F");
        day3high.setText("High: " + dailyForecast.getHigh(3) + (char) 0x00B0 + " F");

        day4weekday.setText(dailyForecast.getDate(4));
        day4condition.setText(dailyForecast.getCondition(4));
        day4low.setText("Low: " + dailyForecast.getLow(4) + (char) 0x00B0 + " F");
        day4high.setText("High: " + dailyForecast.getHigh(4) + (char) 0x00B0 + " F");

        day5weekday.setText(dailyForecast.getDate(5));
        day5condition.setText(dailyForecast.getCondition(5));
        day5low.setText("Low: " + dailyForecast.getLow(5) + (char) 0x00B0 + " F");
        day5high.setText("High: " + dailyForecast.getHigh(5) + (char) 0x00B0 + " F");

        day6weekday.setText(dailyForecast.getDate(6));
        day6condition.setText(dailyForecast.getCondition(6));
        day6low.setText("Low: " + dailyForecast.getLow(6) + (char) 0x00B0 + " F");
        day6high.setText("High: " + dailyForecast.getHigh(6) + (char) 0x00B0 + " F");

        day7weekday.setText(dailyForecast.getDate(7));
        day7condition.setText(dailyForecast.getCondition(7));
        day7low.setText("Low: " + dailyForecast.getLow(7) + (char) 0x00B0 + " F");
        day7high.setText("High: " + dailyForecast.getHigh(7) + (char) 0x00B0 + " F");
    }

    public void getHourlyRequest(){
        new HourlyForecastAST(city, state){
            @Override
            public void onPostExecute(HourlyObject result){
                hourlyForecast = result;
                displayHourlyResults();
            }
        }.execute();
    }

    public void getCurrentRequest(){
        new CurrentConditionAST(city, state){
            @Override
            protected void onPostExecute(ConditionsObject item) {
                currentForecast = item;
                displayCurrentResults();
            }
        }.execute();
    }

    public void getDailyRequest(){
        new DailyForecastAST(city, state){
            @Override
            protected void onPostExecute(DailyObject item) {
                dailyForecast = item;
                displayDailyResults();
            }
        }.execute();
    }

// Begin code for tab 4

    @Override
    public void onMapReady(GoogleMap map) {

        // Get a handle to the googleMap
        maps = map;

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

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                Context context = getApplicationContext();

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                //ImageView weather = new ImageView(context);
                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setGravity(Gravity.CENTER);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                //info.addView(weather);
                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        // First start, configure the map to how we want it (center upon current location, and enable button to focus on current location)
        if (firstStart == 1) {
            maps.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
            maps.setMyLocationEnabled(true);
            firstStart = 0;
        }

    }

    public void placeMarkers(View view) {
        //If the starting and ending points have been initialized then set the markers on the map
        if (startingCoordinates != null && endingCoordinates != null) {
            maps.clear();

            // Get the route between starting and ending destinations
            getRouteBetweenPoints(startingCoordinates, endingCoordinates);

            LatLngBounds tripBounds;
            if ((startingCoordinates.latitude < endingCoordinates.latitude) && (startingCoordinates.longitude < endingCoordinates.longitude)) {
                // Constructor of LatLngBounds(LatLng southwest, LatLng northeast) is satisfied, just create the LatLngBounds object.
                tripBounds = new LatLngBounds(startingCoordinates, endingCoordinates);
            }

            else if (startingCoordinates.latitude < endingCoordinates.latitude && (startingCoordinates.longitude > endingCoordinates.longitude)) {
                // Need to swap starting & ending longitudes to satisfy constructor of LatLngBounds(LatLng southwest, LatLng northeast)
                tripBounds = new LatLngBounds(new LatLng(startingCoordinates.latitude, endingCoordinates.longitude), new LatLng(endingCoordinates.latitude, startingCoordinates.longitude));
            }

            else if ((startingCoordinates.latitude > endingCoordinates.latitude) && (startingCoordinates.longitude < endingCoordinates.longitude)) {
                // Need to swap starting & ending latitudes to satisfy constructor of LatLngBounds(LatLng southwest, LatLng northeast)
                tripBounds = new LatLngBounds(new LatLng(endingCoordinates.latitude, startingCoordinates.longitude), new LatLng(startingCoordinates.latitude, endingCoordinates.longitude));
            }

            else /* (startingCoordinates.latitude > endingCoordinates.latitude) && (startingCoordinates.longitude > endingCoordinates.longitude)*/ {
                // Need to swap starting & ending coordinates to satisfy constructor of LatLngBounds(LatLng southwest, LatLng northeast)
                tripBounds = new LatLngBounds(endingCoordinates, startingCoordinates);
            }
            maps.moveCamera(CameraUpdateFactory.newLatLngBounds(tripBounds, 48));
        }

        //update the map with the corresponding markers for the starting and ending points
        mapFragment.getMapAsync(this);
    }

    public void getRouteBetweenPoints(final LatLng starting, final LatLng ending) {
        new GoogleDirectionsAST(starting, ending) {

            @Override
            protected void onPostExecute(DirectionsObject item) {
                directionsObject = item;

                // Get the epoch time (in seconds) to be used for the Time Zone API
                long timestamp = System.currentTimeMillis()/1000;

                // Store the total distance for interval calculation
                int totalDistance = Integer.parseInt(directionsObject.getRoutesArray().getLegsArray().getDistance().getMeters());

                // Divide the total distance by 8 to get the intervals
                int interval = totalDistance / 7;

                // Create a list of LatLng objects to get the weather for
                weatherPoints = new ArrayList<LatLng>();
                weatherPoints.add(starting);

                // Create a counter to keep track of accumulated distance between intervals
                int counter = 0;

                // Uses overview polyline (not as laggy but will do for now)
                String encodedOverviewPolyline = directionsObject.getRoutesArray().getEncodedOverviewPolyLine().getEncodedOverviewPolyline();
                polyline = com.google.maps.android.PolyUtil.decode(encodedOverviewPolyline);

                // Add all LatLng objects that create the polyline
                for (int i = 0; i < polyline.size() - 1; ++i) {
                    Polyline line = maps.addPolyline(new PolylineOptions().add(polyline.get(i), polyline.get(i+1)).width(5).color(Color.rgb(93,188,210)));

                    // Keep track of which LatLng objects to get weather information for, store each object in weatherPoints
                    counter += com.google.maps.android.SphericalUtil.computeDistanceBetween(polyline.get(i), polyline.get(i+1));
                    if (counter >= interval) {
                        weatherPoints.add(polyline.get(i));
                        counter = counter - interval;
                    }
                }
                weatherPoints.add(ending);

                // Get the distances between the starting point and each point in the interval
                getIntervalInformation(weatherPoints);

                // MOST ACCURATE POLYLINE, BUT EXTREMELY LAGGY!!!
                /*ArrayList<StepsArrayItem> stepsArrayItems = directionsObject.getRoutesArray().getLegsArray().getStepsArray().getStepsArrayItems();
                for(int i = 0; i < stepsArrayItems.size(); ++i) {
                    List<LatLng> encodedPolyline = stepsArrayItems.get(i).getEncodedPolyline().getPolyline();
                    for(int j = 0; j < encodedPolyline.size() - 1; ++j) {
                        Polyline line = maps.addPolyline(new PolylineOptions().add(encodedPolyline.get(j), encodedPolyline.get(j + 1)).width(5).color(Color.RED));
                    }
                }*/
            }
        }.execute();
    }

    public void getIntervalInformation (ArrayList<LatLng> intervals) {

            // For each LatLng object inside weatherPoints
            new IntervalInformationAST(intervals) {

                @Override
                protected void onPostExecute(ArrayList<MarkerOptions> intervalInformation) {
                    markers = intervalInformation;

                    if (markers != null) {
                        for (int i = 0; i < markers.size(); ++i) {
                            maps.addMarker(markers.get(i));
                        }
                    }

                    else {
                        //TODO - Getting the info failed.
                        maps.clear();
                    }

                }

            }.execute();

        mapFragment.getMapAsync(this);
    }

// End Code for tab 4
}