package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class Distance implements JSONData {
    private String distance;

    @Override
    public void retrieveData(JSONObject data) {
        distance = data.optString("text");
    }

    public String getDistance() {
        return distance;
    }
}
