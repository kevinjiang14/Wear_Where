package com.example.kevin.wear_where;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.support.v7.app.AlertDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.wear_where.AsyncTask.DailyForecastAST;
import com.example.kevin.wear_where.AsyncTask.GoogleDirectionsAST;
import com.example.kevin.wear_where.AsyncTask.IntervalInformationAST;
import com.example.kevin.wear_where.Google.Directions.DirectionsObject;
import com.example.kevin.wear_where.Listeners.DateListener;
import com.example.kevin.wear_where.WundergroundData.DailyForecast.DailyObject;
import com.example.kevin.wear_where.wear.Clothing;
import com.example.kevin.wear_where.wear.ClothingActivity;
import com.example.kevin.wear_where.wear.Suggestion;
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
import com.example.kevin.wear_where.Database.Comment;
import com.example.kevin.wear_where.Database.CommentsDataSource;
import com.example.kevin.wear_where.Database.MySQLiteHelper;

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
import java.io.IOException;
import java.util.Calendar;
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

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener leaveDateListener, returnDateListener;
    private EditText leaveDate, returnDate;
    private Button submitButton;

    private SwipeRefreshLayout refreshSwipe;
    private String city;
    private String state;

    private CommentsDataSource datasource;

    private Clothing clothes = new Clothing();
    public final static String MESSAGE = "com.example.kevin.wear_where.MESSAGE";

    private Button menuButton;
    final Context context = this;

    // Global Geocoder class
    private Geocoder geocoder;

    // Intent for new VacationDataActivity
    private Intent vacationData;
    private List<Address> vacationAddresses;


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

        datasource = new CommentsDataSource(this);
        datasource.open();

        //Initialize buttons on tab2
        Button headwear = (Button)findViewById(R.id.headwear);
        Button upperbody = (Button)findViewById(R.id.upperbody);
        Button lowerbody = (Button)findViewById(R.id.lowerbody);
        Button shoes = (Button)findViewById(R.id.shoes);

        //Start onclick functions of buttons
        headwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> headwear = clothes.getHeadWear(currentForecast.getTemperature());
                String[] headwearList = new String[headwear.size()];
                for (int i = 0; i < headwear.size(); i++){
                    headwearList[i] = headwear.get(i);
                }
                Intent myIntent = new Intent(MainActivity.this, ClothingActivity.class);
                myIntent.putExtra(MESSAGE, headwearList);
                startActivity(myIntent);
            }
        });

        upperbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> upperbody = clothes.getUpperBody(currentForecast.getTemperature());
                String[] upperbodyList = new String[upperbody.size()];
                for (int i = 0; i < upperbody.size(); i++){
                    upperbodyList[i] = upperbody.get(i);
                }
                Intent myIntent = new Intent(MainActivity.this, ClothingActivity.class);
                myIntent.putExtra(MESSAGE, upperbodyList);
                startActivity(myIntent);
            }
        });

        lowerbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> lowerbody = clothes.getLowerBody(currentForecast.getTemperature());
                String[] lowerbodyList = new String[lowerbody.size()];
                for (int i = 0; i < lowerbody.size(); i++){
                    lowerbodyList[i] = lowerbody.get(i);
                }
                Intent myIntent = new Intent(MainActivity.this, ClothingActivity.class);
                myIntent.putExtra(MESSAGE, lowerbodyList);
                startActivity(myIntent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> shoes = clothes.getShoes(currentForecast.getTemperature());
                String[] shoesList = new String[shoes.size()];
                for (int i = 0; i < shoes.size(); i++){
                    shoesList[i] = shoes.get(i);
                }
                Intent myIntent = new Intent(MainActivity.this, ClothingActivity.class);
                myIntent.putExtra(MESSAGE, shoesList);
                startActivity(myIntent);
            }
        });
    }

    //Called when the activity becomes visible to the user. Also called when coming back to the application from another application (assuming this application wasn't killed).
    @Override
    protected void onStart() {
        super.onStart();
        vacationData = new Intent(MainActivity.this, VacationDataActivity.class);

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
                Log.i(TAG, "Place: " + place.getName());
                try {
                    vacationAddresses = geocoder.getFromLocation(vacationLatitude, vacationLongitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        menuButton = (Button) findViewById(R.id.button);

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

        // Leave date selector
        leaveDate = (EditText) findViewById(R.id.leaveDate);
        leaveDate.setInputType(InputType.TYPE_NULL);
        calendar = Calendar.getInstance();
        leaveDateListener = new DateListener(leaveDate, calendar);
        leaveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, leaveDateListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Return date selector
        returnDate = (EditText) findViewById(R.id.returnDate);
        returnDate.setInputType(InputType.TYPE_NULL);
        calendar = Calendar.getInstance();
        returnDateListener = new DateListener(returnDate, calendar);
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, returnDateListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        // TODO: Create a class with the predefined method for the onclicklistener
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vacationAddresses.get(0).getCountryCode().equals("US")) {
                    vacationData.putExtra("city", vacationAddresses.get(0).getLocality());
                    vacationData.putExtra("state", vacationAddresses.get(0).getAdminArea());
                }
                else if(vacationAddresses.get(0).getAdminArea() == null) {
                    vacationData.putExtra("city", vacationAddresses.get(0).getLocality());
                    vacationData.putExtra("state", vacationAddresses.get(0).getCountryCode());
                }
                else {
                    vacationData.putExtra("city", vacationAddresses.get(0).getAdminArea());
                    vacationData.putExtra("state", vacationAddresses.get(0).getCountryCode());
                }

                vacationData.putExtra("leaveDate", leaveDate.getText().toString());
                vacationData.putExtra("returnDate", returnDate.getText().toString());
                startActivity(vacationData);
            }
        });


        // Refresh Handler
        refreshSwipe = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refreshSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                displayCurrentResults();
                displayHourlyResults();
                displayDailyResults();
                completeRefresh();
            }
        });
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
        time1.setText("" + hourlyForecast.getHour(0) + ":00" + hourlyForecast.getAMPM(0));
        description1.setText("" + hourlyForecast.getCondition(0));

        temperature2.setText("" + hourlyForecast.getTemperatureF(1) + (char) 0x00B0 + " F");
        time2.setText("" + hourlyForecast.getHour(1) + ":00" + hourlyForecast.getAMPM(1));
        description2.setText("" + hourlyForecast.getCondition(1));

        temperature3.setText("" + hourlyForecast.getTemperatureF(2) + (char) 0x00B0 + " F");
        time3.setText("" + hourlyForecast.getHour(2) + ":00" + hourlyForecast.getAMPM(2));
        description3.setText("" + hourlyForecast.getCondition(2));

        temperature4.setText("" + hourlyForecast.getTemperatureF(3) + (char) 0x00B0 + " F");
        time4.setText("" + hourlyForecast.getHour(3) + ":00" + hourlyForecast.getAMPM(3));
        description4.setText("" + hourlyForecast.getCondition(3));

        temperature5.setText("" + hourlyForecast.getTemperatureF(4) + (char) 0x00B0 + " F");
        time5.setText("" + hourlyForecast.getHour(4) + ":00" + hourlyForecast.getAMPM(4));
        description5.setText("" + hourlyForecast.getCondition(4));

        temperature6.setText("" + hourlyForecast.getTemperatureF(5) + (char) 0x00B0 + " F");
        time6.setText("" + hourlyForecast.getHour(5) + ":00" + hourlyForecast.getAMPM(5));
        description6.setText("" + hourlyForecast.getCondition(5));

        temperature7.setText("" + hourlyForecast.getTemperatureF(6) + (char) 0x00B0 + " F");
        time7.setText("" + hourlyForecast.getHour(6) + ":00" + hourlyForecast.getAMPM(6));
        description7.setText("" + hourlyForecast.getCondition(6));

        temperature8.setText("" + hourlyForecast.getTemperatureF(7) + (char) 0x00B0 + " F");
        time8.setText("" + hourlyForecast.getHour(7) + ":00" + hourlyForecast.getAMPM(7));
        description8.setText("" + hourlyForecast.getCondition(7));

        temperature9.setText("" + hourlyForecast.getTemperatureF(8) + (char) 0x00B0 + " F");
        time9.setText("" + hourlyForecast.getHour(8) + ":00" + hourlyForecast.getAMPM(8));
        description9.setText("" + hourlyForecast.getCondition(8));

        temperature10.setText("" + hourlyForecast.getTemperatureF(9) + (char) 0x00B0 + " F");
        time10.setText("" + hourlyForecast.getHour(9) + ":00" + hourlyForecast.getAMPM(9));
        description10.setText("" + hourlyForecast.getCondition(9));

        temperature11.setText("" + hourlyForecast.getTemperatureF(10) + (char) 0x00B0 + " F");
        time11.setText("" + hourlyForecast.getHour(10) + ":00" + hourlyForecast.getAMPM(10));
        description11.setText("" + hourlyForecast.getCondition(10));

        temperature12.setText("" + hourlyForecast.getTemperatureF(11) + (char) 0x00B0 + " F");
        time12.setText("" + hourlyForecast.getHour(11) + ":00" + hourlyForecast.getAMPM(11));
        description12.setText("" + hourlyForecast.getCondition(11));

        temperature13.setText("" + hourlyForecast.getTemperatureF(12) + (char) 0x00B0 + " F");
        time13.setText("" + hourlyForecast.getHour(12) + ":00" + hourlyForecast.getAMPM(12));
        description13.setText("" + hourlyForecast.getCondition(12));

        temperature14.setText("" + hourlyForecast.getTemperatureF(13) + (char) 0x00B0 + " F");
        time14.setText("" + hourlyForecast.getHour(13) + ":00" + hourlyForecast.getAMPM(13));
        description14.setText("" + hourlyForecast.getCondition(13));

        temperature15.setText("" + hourlyForecast.getTemperatureF(14) + (char) 0x00B0 + " F");
        time15.setText("" + hourlyForecast.getHour(14) + ":00" + hourlyForecast.getAMPM(14));
        description15.setText("" + hourlyForecast.getCondition(14));

        temperature16.setText("" + hourlyForecast.getTemperatureF(15) + (char) 0x00B0 + " F");
        time16.setText("" + hourlyForecast.getHour(15) + ":00" + hourlyForecast.getAMPM(15));
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

            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                location.setText(city + ", " + state);

                getCurrentRequest();
                getHourlyRequest();
                getDailyRequest();
            } catch (Exception e) {
                e.printStackTrace();
            @Override
            public View getInfoWindow(Marker arg0) {
                // Return null so getInfoContents will be called
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                // Create the layout that will be used for the information window
                Context context = getApplicationContext();

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                // TextView used to tell the user if it is a starting, interval, or ending point
                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                // TextView used to tell the user the information pertaining to the corresponding interval
                TextView snippet = new TextView(context);
                snippet.setGravity(Gravity.CENTER);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                // Add the textViews to the LinearLayout
                info.addView(title);
                info.addView(snippet);

                // Return the layout that will be used for the marker information window
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

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    /* Menu code */
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.favtemp:
                        // get prompts.xml view
                        LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.prompt, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);

                        final EditText userInput = (EditText) promptsView.findViewById(R.id.editText);

                        Comment tempComment = datasource.getFirstComment();
                        if(tempComment != null){
                            userInput.setText(tempComment.getComment());
                        }

                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                Comment tempComment = datasource.getFirstComment();
                                                if(tempComment == null){
                                                    datasource.createComment(userInput.getText().toString());
                                                }
                                                else{
                                                    datasource.deleteComment(tempComment);
                                                    datasource.createComment(userInput.getText().toString());
                                                }
                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                        return true;
                    case R.id.search:
                        // get search_input.xml view
                        LayoutInflater li2 = LayoutInflater.from(context);
                        View promptsView2 = li2.inflate(R.layout.search_input, null);

                        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(context);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder2.setView(promptsView2);

                        final EditText cityInput = (EditText) promptsView2.findViewById(R.id.cityInput);
                        final EditText stateInput = (EditText) promptsView2.findViewById(R.id.stateInput);

                        // set dialog message
                        alertDialogBuilder2
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                final String city_input = cityInput.getText().toString();
                                                final String state_input = stateInput.getText().toString();

                                                // get search_result.xml view
                                                LayoutInflater li3 = LayoutInflater.from(context);
                                                View promptsView3 = li3.inflate(R.layout.search_result, null);

                                                AlertDialog.Builder alertDialogBuilder3 = new AlertDialog.Builder(context);

                                                // set prompts.xml to alertdialog builder
                                                alertDialogBuilder3.setView(promptsView3);

                                                final TextView searchResult = (TextView) promptsView3.findViewById(R.id.searchResult);

                                                new CurrentConditionAST(city_input, state_input){
                                                    @Override
                                                    protected void onPostExecute(ConditionsObject item) {
                                                        ConditionsObject resultForecast = item;
                                                        searchResult.setText("The weather in " + city_input + ", " + state_input + " is " + resultForecast.getTemperature() + (char) 0x00B0 + " F");
                                                        displayCurrentResults();
                                                    }
                                                }.execute();

                                                // set dialog message
                                                alertDialogBuilder3
                                                        .setCancelable(false)
                                                        .setPositiveButton("OK",
                                                                new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                        dialog.cancel();
                                                                    }
                                                                });

                                                // create alert dialog
                                                AlertDialog alertDialog3 = alertDialogBuilder3.create();

                                                // show it
                                                alertDialog3.show();
                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog2 = alertDialogBuilder2.create();

                        // show it
                        alertDialog2.show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.inflate(R.menu.menu);
        popup.show();
    }

    public void completeRefresh(){
        refreshSwipe.setRefreshing(false);
    }

    public void getRouteBetweenPoints(final LatLng starting, final LatLng ending) {
        new GoogleDirectionsAST(starting, ending) {

            @Override
            protected void onPostExecute(DirectionsObject item) {
                directionsObject = item;

                // Create a list of LatLng objects to get the weather for
                weatherPoints = new ArrayList<LatLng>();
                weatherPoints.add(starting);

                // Create a counter to keep track of accumulated distance between intervals
                double counter = 0;

                // Uses overview polyline (not as laggy but will do for now)
                String encodedOverviewPolyline = directionsObject.getRoutesArray().getEncodedOverviewPolyLine().getEncodedOverviewPolyline();
                polyline = com.google.maps.android.PolyUtil.decode(encodedOverviewPolyline);

                // Store the total distance for interval calculation
                double totalDistance = Double.parseDouble(directionsObject.getRoutesArray().getLegsArray().getDistance().getMeters());

                // Divide the total distance by 10 to get the intervals
                double interval = totalDistance / 10;

                // Add all LatLng objects that create the polyline
                for (int i = 0; i < polyline.size() - 1; ++i) {
                    // Add the interval polyline to the map
                    maps.addPolyline(new PolylineOptions().add(polyline.get(i), polyline.get(i+1)).width(8).color(Color.rgb(93,188,210)));

                    // Keep track of which LatLng objects to get weather information for, store each object in weatherPoints
                    counter += com.google.maps.android.SphericalUtil.computeDistanceBetween(polyline.get(i), polyline.get(i+1));
                    if (counter >= interval) {
                        weatherPoints.add(polyline.get(i));
                        counter = 0 - com.google.maps.android.SphericalUtil.computeDistanceBetween(polyline.get(i), polyline.get(i+1));
                    }
                }

                // Remove last added LatLng to avoid a LatLng object similar to the destination LatLng
                if (weatherPoints.size() == 10) {
                    weatherPoints.remove(weatherPoints.size() - 1);
                }

                // Add the final interval point
                weatherPoints.add(ending);

                // Get the information pertaining to each interval in weatherPoints
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

            // Get the MarkerOptions for the intervals passed into the markers
            new IntervalInformationAST(intervals) {

                @Override
                protected void onPostExecute(ArrayList<MarkerOptions> intervalInformation) {
                    markers = intervalInformation;

                    // If the returned list is not null, then add the markers to the map
                    if (markers != null) {
                        for (int i = 0; i < markers.size(); ++i) {
                            maps.addMarker(markers.get(i));
                        }
                    }

                    // Else, notify the user that the attempt to get information has failed.
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "An error has occured while trying to fetch the queried route." + '\n' + "Please try again!", Toast.LENGTH_SHORT);
                        toast.show();
                        maps.clear();
                    }

                }

            }.execute();

        // Refresh the map
        mapFragment.getMapAsync(this);
    }

// End Code for tab 4
}