package com.example.kevin.wear_where.AsyncTask;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.kevin.wear_where.WundergroundData.HourlyForecast.HourlyItem;
import com.example.kevin.wear_where.WundergroundData.HourlyForecast.Temperature;
import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hermes on 10/23/2016.
 */

public class MapsForecastAST extends AsyncTask<Void, Void, HourlyItem> {
    private URL request;
    private LatLng latlng;
    private int index;
    private int temperature;
    private String condition;
    private String iconURL;
    private int flag;

    public MapsForecastAST(LatLng latlng, int index){
        this.latlng = latlng;
        this.index = index;
    }

    @Override
    protected HourlyItem doInBackground(Void... params) {
        if (index == -1) {
            return null;
        }

        HourlyItem hourlyForecastTemp;
        flag = 0; // Flag to keep track of how many things we've read so far
        String condition_link = String.format("http://api.wunderground.com/api/fe0b389aa655786c/hourly10day/q/%s,%s.json", Uri.encode(Double.toString(this.latlng.latitude)), Uri.encode(Double.toString(this.latlng.longitude)));

        hourlyForecastTemp = this.loop(condition_link);

        // If the connection failed, try again.
        if (hourlyForecastTemp.getCondition() == null && hourlyForecastTemp.getIconURL() == null) {
            System.out.println("Failed, trying again...");
            flag = 0;
            hourlyForecastTemp = this.loop(condition_link);
        }

        // If the connection failed, try again.
        if (hourlyForecastTemp.getCondition() == null && hourlyForecastTemp.getIconURL() == null) {
            System.out.println("Failed, trying again for a second time...");
            flag = 0;
            hourlyForecastTemp = this.loop(condition_link);
        }

        // Connection has failed at this point...bad internet? API Down?
        if (hourlyForecastTemp.getCondition() == null && hourlyForecastTemp.getIconURL() == null) {
            System.out.println("Failed to retrieve weather information!");
        }

        return hourlyForecastTemp;
    }

    private HourlyItem loop (String link) {
        try {
            request = new URL(link);
            System.out.println(link);

            // Open a URL connection to link
            URLConnection urlConnection = request.openConnection();

            // Get the input stream of link
            InputStream in = urlConnection.getInputStream();

            // Read buffer
            JsonReader reader = new JsonReader(new InputStreamReader(in));

            // Store the buffer of link into result
            StringBuilder result = new StringBuilder();

            // Store each line of buffer into line
            String line;

            // Get parse relevant information from JSON
            readHourlyForecast10Day(reader);
            reader.close();

            return new HourlyItem(temperature, condition, iconURL);
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void readHourlyForecast10Day (JsonReader reader) {
        try {
            // Begin reading the JSON File
            reader.beginObject();

            // While there are more entities to read
            while(reader.hasNext()) {
                // Get the name of the next object
                String name = reader.nextName();

                // If the object is "hourly_forecast", read the object
                if (name.equals("hourly_forecast")) {
                    readHourlyForecast(reader);
                    break;
                }
                // Else, skip the object
                else {
                    reader.skipValue();
                }
            }
            // Don't need to end object because we will close the reader once we read what we need
            //reader.endObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHourlyForecast (JsonReader reader) {
        try {
            // Begin reading the "hourly_forecast" array
            reader.beginArray();

            // While there are more entities to read
            while (reader.hasNext()) {

                // If index is 0, it means we've skipped enough objects and need to read the current hourly object
                if (index == 0) {
                    this.readHourlyObject(reader);
                    break; // Break to stop reading
                }

                // Else skip the object
                else {
                    reader.skipValue();
                    index--; // Subtract 1 from index to keep track of how many objects we've skipped
                }
            }
            //reader.endArray(); // Don't need to end object because we will close the reader once we read what we need
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHourlyObject (JsonReader reader) {
        try {
            // Begin reading the hourly object
            reader.beginObject();

            // While there are more entities to be read
            while (reader.hasNext()) {

                // Get the name of the next entity
                String name = reader.nextName();

                // Read the "temp" object
                if (name.equals("temp")) {

                    // Begin reading the "temp" object
                    reader.beginObject();
                    while (reader.hasNext()) {

                        // Get the name of the next entity
                        name = reader.nextName();

                        // Read the temperature in english (farenheit)
                        if (name.equals("english")) {
                            temperature = Integer.parseInt(reader.nextString());
                            flag++; // Increment the flag so we can tell if we've finished reading what we needed to read
                        }

                        // Skip the elements we don't need
                        else {
                            reader.skipValue();
                        }
                    }
                    // End the object because we still need to read condition and icon url
                    reader.endObject();
                }

                // Read the "condition" string
                else if (name.equals("condition")) {

                    // Read the current condition
                    condition = reader.nextString();
                    flag++; // Increment the flag so we can tell if we've finished reading what we needed to read
                }

                // Read the "icon_url" string
                else if (name.equals("icon_url")) {

                    // Read the icon url
                    iconURL = reader.nextString();
                    flag++; // Increment the flag so we can tell if we've finished reading what we needed to read
                }

                // If flag == 3, we've read the 3 elements we needed to read (Temperature, Condition, IconURL), so break
                else if (flag == 3) {
                    break;
                }

                // Else skip the object
                else {
                    reader.skipValue();
                }
            }
            // Don't need to end object because we will close the reader once we read what we need
            //reader.endObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}