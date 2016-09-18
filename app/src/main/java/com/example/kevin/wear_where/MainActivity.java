package com.example.kevin.wear_where;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kevin.wear_where.data.Channel;
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

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    TextView temperature, location, description;            // TextView in xml
    URL request;                                            // The link requesting service from Yahoo query
    Channel channel;                                        // Channel Object

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

        temperature = (TextView) findViewById(R.id.temperature);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.description);

        channel = new Channel();
        getRequest();

        homeTab = tabHost.newTabSpec("homeTab2");
        homeTab.setIndicator("Apparel");
        homeTab.setContent(R.id.layout2);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab3");
        homeTab.setIndicator("Vacation?");
        homeTab.setContent(R.id.layout3);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab2");
        homeTab.setIndicator("Road\nTrip!");
        homeTab.setContent(R.id.layout4);
        tabHost.addTab(homeTab);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
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

                String city = "buffalo";
                String state = "NY";

                String link = String.format("http://api.wunderground.com/api/ca5b9df3415b7849/hourly/q/%s/%s.json", Uri.encode(state), Uri.encode(city));

                //String query = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"buffalo, ny\")");

                //String link = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(query));

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
}