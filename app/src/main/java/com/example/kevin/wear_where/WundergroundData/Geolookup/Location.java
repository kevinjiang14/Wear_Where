package com.example.kevin.wear_where.WundergroundData.Geolookup;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/21/16.
 */

public class Location implements JSONData {

    private String city;
    private String state;

    @Override
    public void retrieveData(JSONObject data) {
        city = data.optString("city");
        state = data.optString("state");
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }
}
