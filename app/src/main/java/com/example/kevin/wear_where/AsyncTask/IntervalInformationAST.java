package com.example.kevin.wear_where.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.kevin.wear_where.Google.Distance.DistanceMatrixObject;
import com.example.kevin.wear_where.Google.TimeZone.TimeZoneObject;
import com.example.kevin.wear_where.WundergroundData.HourlyForecast.HourlyObject;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Hermes on 10/23/2016.
 */

public class IntervalInformationAST extends AsyncTask<Void, Void, ArrayList<MarkerOptions>> {

    private URL request;
    private ArrayList<LatLng> intervals;
    private long timestamp;

    public IntervalInformationAST(ArrayList<LatLng> intervals) {
        this.intervals = intervals;
        this.timestamp = System.currentTimeMillis()/1000;
    }

    @Override
    protected ArrayList<MarkerOptions> doInBackground(Void... params) throws IllegalStateException{

        ArrayList<MarkerOptions> intervalInformation = new ArrayList<>();
        ArrayList<TimeZoneObject> intervalTimeZones = new ArrayList<>();
        ArrayList<HourlyObject> mapsHourlyForecast = new ArrayList<>();
        DistanceMatrixObject distanceMatrixObject = null;

        // Begin GoogleDistanceAST
        String origins = new String(Double.toString(this.intervals.get(0).latitude) + "," + Double.toString(this.intervals.get(0).longitude));
        String destinations = new String(Double.toString(this.intervals.get(0).latitude) + "," + Double.toString(this.intervals.get(0).longitude));
        for (int i = 1; i < intervals.size(); ++i) {
            destinations += "|" + Double.toString(this.intervals.get(i).latitude) + "," + Double.toString(this.intervals.get(i).longitude);
        }

        //https://maps.googleapis.com/maps/api/distancematrix/outputFormat?parameters
        String condition_link = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=AIzaSyCWbLdotKFPVqlld5nr8haQhjrh70xhXqA", Uri.encode(origins), Uri.encode(destinations));

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
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Begin GoogleTimeZoneAST & MapsForecastAST
        for (int i = 0; i < intervals.size(); ++i) {

            //https://maps.googleapis.com/maps/api/timezone/outputFormat?parameters
            condition_link = String.format("https://maps.googleapis.com/maps/api/timezone/json?location=%s&timestamp=%s&key=AIzaSyCWbLdotKFPVqlld5nr8haQhjrh70xhXqA", Uri.encode(Double.toString(this.intervals.get(i).latitude) + "," + Double.toString(this.intervals.get(i).longitude)), Uri.encode(Long.toString(this.timestamp)));

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

                intervalTimeZones.add(new TimeZoneObject(result));
            }

            catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            condition_link = String.format("http://api.wunderground.com/api/fe0b389aa655786c/hourly10day/q/%s,%s.json", Uri.encode(Double.toString(this.intervals.get(i).latitude)), Uri.encode(Double.toString(this.intervals.get(i).longitude)));

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

                mapsHourlyForecast.add(new HourlyObject(result));
            }

            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        if (distanceMatrixObject != null) {
            if ((intervals.size() == intervalTimeZones.size()) && (intervals.size() ==  mapsHourlyForecast.size())) {

                // Create the list of MarkerOptions to add to the map
                for (int i = 0; i < intervals.size(); ++i) {
                    Bitmap bitmap;
                    Long currentTime = System.currentTimeMillis() - ((new GregorianCalendar().getTimeZone().getRawOffset()) + new GregorianCalendar().getTimeZone().getDSTSavings());
                    Long intervalDuration = Long.parseLong((distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDuration().getSeconds())) * 1000;
                    Long endingDSTOffset = Long.parseLong(intervalTimeZones.get(i).getDstOffset()) * 1000;
                    Long endingRawOffset = Long.parseLong(intervalTimeZones.get(i).getRawOffset()) * 1000;
                    String estimatedTimeOfArrival = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new java.util.Date(currentTime + intervalDuration + (endingDSTOffset + endingRawOffset))) + '\n' + intervalTimeZones.get(i).getTimeZoneName();
                    String precipitation = mapsHourlyForecast.get(i).getCondition((int) (intervalDuration / (3600 * 1000)));
                    String temperatureF = Integer.toString(mapsHourlyForecast.get(i).getTemperatureF((int) (intervalDuration / (3600 * 1000)))) + (char) 0x00B0 + " F";
                    String iconURL = mapsHourlyForecast.get(i).getIconURL((int) (intervalDuration / (3600*1000)));

                    try {
                        URL request = new URL(iconURL);
                        HttpURLConnection urlConnection = (HttpURLConnection) request.openConnection();
                        urlConnection.setDoInput(true);
                        urlConnection.connect();
                        InputStream input = urlConnection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(input);
                    }

                    catch (Exception e) {
                        return null;
                    }

                    if (i == 0) {
                        intervalInformation.add(new MarkerOptions().position(intervals.get(0))
                                .title("Origin")
                                .snippet(distanceMatrixObject.getDestinationAddressesArray().getDestinationAddressesArrayItems().get(0) + '\n' +
                                        "Distance Travelled: " + distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDistance().getDistance() + '\n' +
                                        "Time elapsed: " + distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDuration().getDuration() + "\n\n" +
                                        "Time at beginning of trip: " + '\n' + estimatedTimeOfArrival + "\n\n" +
                                        "Weather at ETA: " + '\n' + precipitation + '\n' + temperatureF)
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                    }

                    else if (i == intervals.size() - 1) {
                        intervalInformation.add(new MarkerOptions().position(intervals.get(i))
                                .title("Destination")
                                .snippet(distanceMatrixObject.getDestinationAddressesArray().getDestinationAddressesArrayItems().get(i) + '\n' +
                                        "Distance Travelled: " + distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDistance().getDistance() + '\n' +
                                        "Time elapsed: " + distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDuration().getDuration() + "\n\n" +
                                        "Estimated Arrival Time: " + '\n' + estimatedTimeOfArrival + "\n\n" +
                                        "Weather at ETA: " + '\n' + precipitation + '\n' + temperatureF)
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                    }

                    else {
                        intervalInformation.add(new MarkerOptions().position(intervals.get(i))
                                .title("Interval")
                                .snippet(distanceMatrixObject.getDestinationAddressesArray().getDestinationAddressesArrayItems().get(i) + '\n' +
                                        "Distance Travelled: " + distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDistance().getDistance() + '\n' +
                                        "Time elapsed: " + distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDuration().getDuration() + "\n\n" +
                                        "Estimated Arrival Time: " + '\n' + estimatedTimeOfArrival + "\n\n" +
                                        "Weather at ETA: " + '\n' + precipitation + '\n' + temperatureF)
                                .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                    }
                }
            }

            else {
                return null;
            }
        }

        else {
            return null;
        }

        return intervalInformation;
    }
}
