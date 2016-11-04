package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.kevin.wear_where.WundergroundData.HourlyForecast.HourlyItem;
import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hermes on 10/23/2016.
 */

public class MapsForecastAST extends AsyncTask<Void, Void, HourlyItem> {
    private URL request;
    private LatLng latlng;
    private int index;
    private int temperature;
    private String condition;
    private String iconURL;

    public MapsForecastAST(LatLng latlng, int index){
        this.latlng = latlng;
        this.index = index;
    }

    @Override
    protected HourlyItem doInBackground(Void... params) {
        HourlyItem hourlyForecastTemp;

        String condition_link = String.format("http://api.wunderground.com/api/fe0b389aa655786c/hourly10day/q/%s,%s.json", Uri.encode(Double.toString(this.latlng.latitude)), Uri.encode(Double.toString(this.latlng.longitude)));

        hourlyForecastTemp = this.loop(condition_link);

        // If the connection failed, try again.
        if (hourlyForecastTemp.getCondition() == null) {
            System.out.println("failed");
            hourlyForecastTemp = this.loop(condition_link);
        }

        return hourlyForecastTemp;
    }

    private HourlyItem loop (String link) {
        try {
            request = new URL(link);
            System.out.println(link);

            // Open a URL connection to link
            URLConnection urlConnection = request.openConnection();

            // Get the input stream of link
            InputStream in = urlConnection.getInputStream();

            // Read buffer
            JsonReader reader = new JsonReader(new InputStreamReader(in));

            // Store the buffer of link into result
            StringBuilder result = new StringBuilder();

            // Store each line of buffer into line
            String line;

            // Get parse relevant information from JSON
            readHourlyForecast10Day(reader);
            reader.close();

            System.out.println(temperature);
            System.out.println(condition);
            System.out.println(iconURL);
            return new HourlyItem(temperature, condition, iconURL);
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void readHourlyForecast10Day (JsonReader reader) {
        try {
            reader.beginObject();
            while(reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("hourly_forecast")) {
                    readHourlyForecast(reader);
                }
                else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHourlyForecast (JsonReader reader) {
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                if (index == 0) {
                    this.readHourlyObject(reader);
                    index--;
                }
                else {
                    reader.skipValue();
                    index--;
                }
            }
            reader.endArray();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHourlyObject (JsonReader reader) {
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                // Find the elements we need
                String name = reader.nextName();

                // Read the "temp" object
                if (name.equals("temp")) {

                    // Begin reading the "temp" object
                    reader.beginObject();
                    while (reader.hasNext()) {

                        // Find the elements we need
                        name = reader.nextName();
                        if (name.equals("english")) {
                            temperature = Integer.parseInt(reader.nextString());
                        }

                        // Skip the elements we don't need
                        else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                }

                // Read the "condition" string
                else if (name.equals("condition")) {
                    condition = reader.nextString();
                }

                // Read the "icon_url" string
                else if (name.equals("icon_url")) {
                    iconURL = reader.nextString();
                }

                else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}