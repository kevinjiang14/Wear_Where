package com.example.kevin.wear_where.WundergroundData.CurrentCondition;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/20/16.
 */

public class DisplayLocation implements JSONData{

    private String location;

    @Override
    public void retrieveData(JSONObject data) {
        location = data.optString("full");
    }

    public String getLocation() {
        return location;
    }
}
