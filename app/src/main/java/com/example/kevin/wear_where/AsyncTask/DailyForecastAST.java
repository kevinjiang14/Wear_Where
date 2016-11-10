package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.WundergroundData.CurrentCondition.ConditionsObject;
import com.example.kevin.wear_where.WundergroundData.DailyForecast.DailyObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin Jiang on 10/12/16.
 */

public class DailyForecastAST extends AsyncTask<Void, DailyObject, DailyObject>{

    private String city, state;
    private URL request;

    public DailyForecastAST(String city, String state){
        this.city = city;
        this.state = state;
    }

    @Override
    protected DailyObject doInBackground(Void... params) {
        DailyObject dailyObject;

        String daily_link = String.format("http://api.wunderground.com/api/ad52b6bffd967fae/forecast10day/q/%s/%s.json", Uri.encode(state), Uri.encode(city));

        try {
            request = new URL(daily_link);
            System.out.println(daily_link);
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

            dailyObject = new DailyObject(result);

            return dailyObject;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

