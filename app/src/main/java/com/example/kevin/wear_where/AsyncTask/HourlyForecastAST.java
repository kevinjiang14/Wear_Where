package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.WundergroundData.HourlyForecast.HourlyObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin Jiang on 10/4/16.
 */

public class HourlyForecastAST extends AsyncTask<Void, Void, HourlyObject> {

    private URL request;
    private String city;
    private String state;

    public HourlyForecastAST(String city, String state){
        this.city = city;
        this.state = state;
    }

    @Override
    protected HourlyObject doInBackground(Void... params) {
        HourlyObject hourlyForecastTemp;

        String condition_link = String.format("http://api.wunderground.com/api/ca5b9df3415b7849/hourly/q/%s/%s.json", Uri.encode(state), Uri.encode(city));

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

            hourlyForecastTemp = new HourlyObject(result);

            return hourlyForecastTemp;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
