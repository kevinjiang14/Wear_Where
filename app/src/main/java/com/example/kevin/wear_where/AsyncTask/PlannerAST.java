package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

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


    public PlannerAST(String leaveDate, String returnDate, String city, String state){
        this.leaveDate = leaveDate;
        this.returnDate = returnDate;
        this.city = city;
        this.state = state;
    }

    @Override
    protected PlannerObject doInBackground(Void... params) {
        PlannerObject plannerObject;

        String planner_link = String.format("http://api.wunderground.com/api/fe0b389aa655786c/planner_%s%s/q/%s/%s.json", Uri.encode(leaveDate), Uri.encode(returnDate), Uri.encode(state), Uri.encode(city));

        plannerObject = this.loop(planner_link);

        int counter = 0;

        // If the connection failed, try again.
        while (plannerObject == null && counter < 3) {
            plannerObject = this.loop(planner_link);
            counter++;
        }

        return plannerObject;
    }

    @Nullable
    private PlannerObject loop (String link) {
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

            return new PlannerObject(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
