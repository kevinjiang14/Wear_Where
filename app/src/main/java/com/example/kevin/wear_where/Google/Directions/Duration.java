package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class Duration implements JSONData {
    private String duration;

    @Override
    public void retrieveData(JSONObject data) {
        duration = data.optString("text");
    }

    public String getDuration() {
        return duration;
    }
}
