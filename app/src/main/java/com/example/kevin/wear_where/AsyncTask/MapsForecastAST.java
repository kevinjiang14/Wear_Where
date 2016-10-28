package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.WundergroundData.HourlyForecast.HourlyObject;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hermes on 10/23/2016.
 */

public class MapsForecastAST extends AsyncTask<Void, Void, HourlyObject> {
    private URL request;
    private LatLng latlng;

    public MapsForecastAST(LatLng latlng){
        this.latlng = latlng;
    }

    @Override
    protected HourlyObject doInBackground(Void... params) {
        HourlyObject hourlyForecastTemp;

        String condition_link = String.format("http://api.wunderground.com/api/fe0b389aa655786c/hourly10day/q/%s,%s.json", Uri.encode(Double.toString(this.latlng.latitude)), Uri.encode(Double.toString(this.latlng.longitude)));

        hourlyForecastTemp = this.loop(condition_link);

        // If the connection failed, try again.
        if (hourlyForecastTemp == null) {
            hourlyForecastTemp = this.loop(condition_link);
        }

        return hourlyForecastTemp;
    }

    private HourlyObject loop (String link) {
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

            return new HourlyObject(result);
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}