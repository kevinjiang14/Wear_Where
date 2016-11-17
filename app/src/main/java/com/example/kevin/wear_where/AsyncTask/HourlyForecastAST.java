package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

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
        HourlyObject hourlyObject;

        String hourly_link = String.format("http://api.wunderground.com/api/ad52b6bffd967fae/hourly/q/%s/%s.json", Uri.encode(state), Uri.encode(city));

        hourlyObject = this.loop(hourly_link);

        int counter = 0;

        // If the connection failed, try again.
        while (hourlyObject == null && counter < 3) {
            hourlyObject = this.loop(hourly_link);
            counter++;
        }

        return hourlyObject;
    }

    @Nullable
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

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
