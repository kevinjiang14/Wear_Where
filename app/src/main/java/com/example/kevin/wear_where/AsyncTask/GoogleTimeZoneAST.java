package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.Google.TimeZone.TimeZoneObject;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hermes on 10/24/2016.
 */

public class GoogleTimeZoneAST extends AsyncTask<Void, Void, TimeZoneObject> {

    private URL request;
    private LatLng destination;
    private long timestamp;

    public GoogleTimeZoneAST(LatLng ending, long timestamp) {
        this.destination = ending;
        this.timestamp = timestamp;
    }


    @Override
    protected TimeZoneObject doInBackground(Void... params) {
        TimeZoneObject timeZoneObject;

        //https://maps.googleapis.com/maps/api/timezone/outputFormat?parameters
        String condition_link = String.format("https://maps.googleapis.com/maps/api/timezone/json?location=%s&timestamp=%s&key=AIzaSyCWbLdotKFPVqlld5nr8haQhjrh70xhXqA", Uri.encode(Double.toString(this.destination.latitude) + "," + Double.toString(this.destination.longitude)), Uri.encode(Long.toString(this.timestamp)));
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

            timeZoneObject = new TimeZoneObject(result);
            return timeZoneObject;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}