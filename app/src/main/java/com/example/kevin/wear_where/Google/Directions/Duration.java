package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class Duration implements JSONData {
    private String duration;
    private String seconds;

    @Override
    public void retrieveData(JSONObject data) {
        duration = data.optString("text");
        seconds = data.optString("value");
    }

    public String getDuration() {
        return duration;
    }

    public String getSeconds() {
        return seconds;
    }
}
