package com.example.kevin.wear_where;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kevin.wear_where.AsyncTask.CurrentConditionAST;
import com.example.kevin.wear_where.AsyncTask.HourlyForecastAST;
import com.example.kevin.wear_where.AsyncTask.ImageLoaderAST;
import com.example.kevin.wear_where.WundergroundData.CurrentCondition.ConditionsObject;

import com.example.kevin.wear_where.WundergroundData.HourlyForecast.HourlyObject;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URL;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    private TextView temperature, location, description;            // TextView for current weather information
    private ImageView conditionIcon;                                // ImageView for current condition image
    private ConditionsObject currentForecast;                       // Object holding the current weather information
    private HourlyObject hourlyForecast;                            // Object holding the hourly weather information

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


    private String city = "Buffalo";
    private String state = "NY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec homeTab = tabHost.newTabSpec("homeTab");
        homeTab.setIndicator("Weather");
        homeTab.setContent(R.id.layout1);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab2");
        homeTab.setIndicator("Apparel");
        homeTab.setContent(R.id.layout2);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab3");
        homeTab.setIndicator("Vacation?");
        homeTab.setContent(R.id.layout3);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab4");
        homeTab.setIndicator("Road\nTrip!");
        homeTab.setContent(R.id.layout4);
        tabHost.addTab(homeTab);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        getCurrentRequest();
        getHourlyRequest();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }

    private void displayCurrentResults() {
        String iconURL = currentForecast.getConditionImageLink();

        new ImageLoaderAST(iconURL, conditionIcon).execute();

        temperature.setText("" + currentForecast.getTemperature() + (char) 0x00B0 + " F");

        description.setText("" + currentForecast.getCondition());

        location.setText("" + city + ", " + state);

        // Debugger
        //Log.d("WearWhere", "" + channel.getItem().getCondition().getTemperature());
        //temperature.setText("" + channel.getLocation());
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
}