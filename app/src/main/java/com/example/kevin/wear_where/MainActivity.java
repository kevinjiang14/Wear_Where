package com.example.kevin.wear_where;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kevin.wear_where.WundergroundData.CurrentCondition.ConditionsObject;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    TextView temperature, location, description;            // TextView in xml
    URL request;                                            // The link requesting service from Yahoo query
    ConditionsObject forecast;

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
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.description);

        getRequest();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }

    private void displayResults() {

        temperature.setText("" + forecast.getTemperature() + (char) 0x00B0 + " F");

        description.setText("" + forecast.getCondition());

        // Debugger
        //Log.d("WearWhere", "" + channel.getItem().getCondition().getTemperature());
        //temperature.setText("" + channel.getLocation());
    }

    public void getRequest() {
        new AsyncTask<Void, ConditionsObject, ConditionsObject>() {

            @Override
            protected ConditionsObject doInBackground(Void... params) {
                ConditionsObject forecastTemp;

                String city = "buffalo";
                String state = "NY";

                String condition_link = String.format("http://api.wunderground.com/api/ca5b9df3415b7849/conditions/q/%s/%s.json", Uri.encode(state), Uri.encode(city));

                try {
                    request = new URL(condition_link);
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

                    forecastTemp = new ConditionsObject(result);

                    return forecastTemp;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(ConditionsObject item) {
                forecast = item;
                displayResults();
            }
        }.execute();
    }
}