package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.Google.Distance.DistanceMatrixObject;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hermes on 10/23/2016.
 */

public class GoogleDistanceAST extends AsyncTask<Void, Void, DistanceMatrixObject> {

    private URL request;
    private ArrayList<LatLng> intervals;

    public GoogleDistanceAST (ArrayList<LatLng> intervals) {
        this.intervals = intervals;
    }

    public GoogleDistanceAST (List<LatLng> intervals) {
        this.intervals = new ArrayList<>();
        this.intervals.addAll(intervals);
    }

    @Override
    protected DistanceMatrixObject doInBackground(Void... params) {
        DistanceMatrixObject distanceMatrixObject;

        String origins = new String(Double.toString(this.intervals.get(0).latitude) + "," + Double.toString(this.intervals.get(0).longitude));
        String destinations = new String(Double.toString(this.intervals.get(0).latitude) + "," + Double.toString(this.intervals.get(0).longitude));
        for (int i = 1; i < intervals.size(); ++i) {
            destinations += "|" + Double.toString(this.intervals.get(i).latitude) + "," + Double.toString(this.intervals.get(i).longitude);
        }

        //https://maps.googleapis.com/maps/api/distancematrix/outputFormat?parameters
        String condition_link = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=AIzaSyCWbLdotKFPVqlld5nr8haQhjrh70xhXqA", Uri.encode(origins), Uri.encode(destinations));
        System.out.println(condition_link);
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

            distanceMatrixObject = new DistanceMatrixObject(result);
            return distanceMatrixObject;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}