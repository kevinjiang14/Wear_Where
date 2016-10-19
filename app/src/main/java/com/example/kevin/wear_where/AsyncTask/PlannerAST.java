package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.WundergroundData.CurrentCondition.ConditionsObject;
import com.example.kevin.wear_where.WundergroundData.Planner.PlannerObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin Jiang on 10/19/16.
 */

public class PlannerAST extends AsyncTask<Void, PlannerObject, PlannerObject> {

    private String city, state;
    private String leaveDate, returnDate;
    private URL request;


    public PlannerAST(){

    }

    @Override
    protected PlannerObject doInBackground(Void... params) {
        PlannerObject plannerObject;

        String planner_link = String.format("http://api.wunderground.com/api/ad52b6bffd967fae/planner_%s%s/q/%s/%s.json", Uri.encode(leaveDate), Uri.encode(returnDate), Uri.encode(state), Uri.encode(city));


        try {
            request = new URL(planner_link);
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

            plannerObject = new PlannerObject(result);

            return plannerObject;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
