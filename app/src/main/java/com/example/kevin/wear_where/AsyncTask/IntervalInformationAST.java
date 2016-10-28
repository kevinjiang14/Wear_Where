package com.example.kevin.wear_where.AsyncTask;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
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

    private ArrayList<LatLng> intervals;
    private ArrayList<MarkerOptions> intervalInformation;
    private ArrayList<TimeZoneObject> intervalTimeZones;
    private ArrayList<HourlyObject> mapsHourlyForecast;
    private DistanceMatrixObject distanceMatrixObject;

    public IntervalInformationAST(ArrayList<LatLng> intervals) {
        this.intervals = intervals;

        intervalInformation = new ArrayList<>();
        intervalTimeZones = new ArrayList<>();
        mapsHourlyForecast = new ArrayList<>();

        distanceMatrixObject = null;

        // Begin GoogleDistanceAST
        new GoogleDistanceAST(intervals) {

            @Override
            protected void onPostExecute(DistanceMatrixObject item) {
                distanceMatrixObject = item;
            }

        }.execute();

        // Begin MapsForecastAST
        for (int i = 0; i < intervals.size(); ++i) {

            new MapsForecastAST(intervals.get(i)) {

                @Override
                protected void onPostExecute(HourlyObject item) {
                    mapsHourlyForecast.add(item);
                }

            }.execute();

        }

        for (int i = 0; i < intervals.size(); ++i) {

            new GoogleTimeZoneAST(intervals.get(i), System.currentTimeMillis()/1000) {

                @Override
                protected void onPostExecute(TimeZoneObject item) {
                    intervalTimeZones.add(item);
                }

            }.execute();

        }
    }

    @Override
    protected ArrayList<MarkerOptions> doInBackground(Void... params) throws IllegalStateException {

        // Don't proceed until we have the distanceMatrixObject
        while (distanceMatrixObject == null) {
        }

        // Don't proceed until we have all information for all intervals
        while (intervals.size() != mapsHourlyForecast.size() && intervals.size() != intervalTimeZones.size()) {
        }

        // Create the list of MarkerOptions to add to the map
        for (int i = 0; i < intervals.size(); ++i) {
            Bitmap bitmap;
            Long currentTime = System.currentTimeMillis() - ((new GregorianCalendar().getTimeZone().getRawOffset()) + new GregorianCalendar().getTimeZone().getDSTSavings());
            Long intervalDuration = Long.parseLong((distanceMatrixObject.getRowsArray().getElementsArray().getElementsArrayItems().get(i).getDuration().getSeconds())) * 1000;
            Long endingDSTOffset = Long.parseLong(intervalTimeZones.get(i).getDstOffset()) * 1000;
            Long endingRawOffset = Long.parseLong(intervalTimeZones.get(i).getRawOffset()) * 1000;
            String estimatedTimeOfArrival = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new java.util.Date(currentTime + intervalDuration + (endingDSTOffset + endingRawOffset))) + '\n' + intervalTimeZones.get(i).getTimeZoneName();
            int index = (int) (intervalDuration / (3600 * 1000));
            String precipitation, temperatureF, iconURL;
                if (index <= 240) {
                    precipitation = mapsHourlyForecast.get(i).getCondition(index);
                    temperatureF = Integer.toString(mapsHourlyForecast.get(i).getTemperatureF(index)) + (char) 0x00B0 + " F";
                    iconURL = mapsHourlyForecast.get(i).getIconURL(index);
                }
                else {
                    precipitation = "Cannot get hourly information more than 10 days ahead!";
                    temperatureF = "";
                    iconURL = "http://www.errorfixz.com/wp-content/uploads/2016/02/question_red.png";
                }
            try {
                URL request = new URL(iconURL);
                HttpURLConnection urlConnection = (HttpURLConnection) request.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.connect();
                InputStream input = urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
                if (bitmap != null) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) (24 * Resources.getSystem().getDisplayMetrics().density), (int) (24 * Resources.getSystem().getDisplayMetrics().density), false);
                }
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

        return intervalInformation;
    }
}
