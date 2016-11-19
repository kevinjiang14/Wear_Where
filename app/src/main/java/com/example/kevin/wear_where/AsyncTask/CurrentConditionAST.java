package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.example.kevin.wear_where.WundergroundData.CurrentCondition.ConditionsObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin Jiang on 9/29/16.
 */

public class CurrentConditionAST extends AsyncTask<Void, ConditionsObject, ConditionsObject> {

    private String city, state;
    private URL request;

    public CurrentConditionAST(String city, String state){
        this.city = city;
        this.state = state;
    }

    @Override
    protected ConditionsObject doInBackground(Void... params) {
        ConditionsObject conditionsObject;

        String current_link = String.format("http://api.wunderground.com/api/ca5b9df3415b7849/conditions/q/%s/%s.json", Uri.encode(state), Uri.encode(city));

        conditionsObject = this.loop(current_link);

        int counter = 0;

        while (conditionsObject == null && counter < 3) {
            // If the connection failed, try again.
            conditionsObject = this.loop(current_link);
            counter++;
        }

        return conditionsObject;
    }

    @Nullable
    private ConditionsObject loop (String link) {
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

            return new ConditionsObject(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
