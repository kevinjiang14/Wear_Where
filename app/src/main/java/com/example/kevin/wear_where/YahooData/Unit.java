package com.example.kevin.wear_where.YahooData;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/11/2016.
 */
public class Unit implements JSONData {

    private String temperatureUnit;

    @Override
    public void retrieveData(JSONObject data) {
        temperatureUnit = data.optString("temperature");
    }

    public String getTemperatureUnit(){
        return temperatureUnit;
    }
}
