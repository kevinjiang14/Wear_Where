package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.Google.Directions.DirectionsObject;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hermes on 10/16/2016.
 */

public class GoogleDirectionsAST extends AsyncTask<Void, Void, DirectionsObject> {

    private URL request;
    private LatLng origin, destination;

    public GoogleDirectionsAST (LatLng starting, LatLng ending) {
        this.origin = starting;
        this.destination = ending;
    }

    @Override
    protected DirectionsObject doInBackground(Void... params) {
        DirectionsObject directionsObject;

        //https://maps.googleapis.com/maps/api/directions/outputFormat?parameters
        String condition_link = String.format("https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=AIzaSyCWbLdotKFPVqlld5nr8haQhjrh70xhXqA", Uri.encode(Double.toString(this.origin.latitude) + "," + Double.toString(this.origin.longitude)), Uri.encode(Double.toString(this.destination.latitude) + "," + Double.toString(this.destination.longitude)));

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

            directionsObject = new DirectionsObject(result);
            return directionsObject;
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
