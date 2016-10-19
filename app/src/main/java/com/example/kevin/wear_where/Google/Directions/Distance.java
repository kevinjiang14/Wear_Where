package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class Distance implements JSONData {
    private String distance;
    private String meters;

    @Override
    public void retrieveData(JSONObject data) {
        distance = data.optString("text");
        meters = data.optString("value");
    }

    public String getDistance() {
        return distance;
    }

    public String getMeters() {
        return meters;
    }
}
