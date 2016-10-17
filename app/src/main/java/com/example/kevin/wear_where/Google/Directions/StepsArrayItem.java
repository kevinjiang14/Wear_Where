package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;
import com.google.android.gms.maps.model.Polyline;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class StepsArrayItem implements JSONData {
    private Distance distance;
    private Duration duration;
    private EncodedPolyline encodedPolyline;

    @Override
    public void retrieveData(JSONObject data) {
        distance = new Distance();
        distance.retrieveData(data.optJSONObject("distance"));

        duration = new Duration();
        duration.retrieveData(data.optJSONObject("duration"));

        encodedPolyline = new EncodedPolyline();
        encodedPolyline.retrieveData(data.optJSONObject("polyline"));
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public EncodedPolyline getEncodedPolyline() {
        return encodedPolyline;
    }
}
