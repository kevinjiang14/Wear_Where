package com.example.kevin.wear_where.AsyncTask;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.example.kevin.wear_where.WundergroundData.Geolookup.GeoLookupObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin Jiang on 10/21/16.
 */

public class GeolookupAST extends AsyncTask<Void, GeoLookupObject, GeoLookupObject> {

    private double latitude, longitude;
    private URL request;

    public GeolookupAST(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected GeoLookupObject doInBackground(Void... params) {
        GeoLookupObject geoLookupObject;

        String geolookupLink = String.format("http://api.wunderground.com/api/ad52b6bffd967fae/geolookup/q/%s,%s.json", Double.toString(latitude), Double.toString(longitude));

        geoLookupObject = this.loop(geolookupLink);

        int counter = 0;

        // If the connection failed, try again.
        if (geoLookupObject == null && counter < 3) {
            geoLookupObject = this.loop(geolookupLink);
            counter++;
        }

        return geoLookupObject;
    }

    @Nullable
    private GeoLookupObject loop (String link) {
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

            return new GeoLookupObject(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
