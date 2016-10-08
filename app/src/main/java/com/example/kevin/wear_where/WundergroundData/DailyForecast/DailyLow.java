package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/27/16.
 */

public class DailyLow implements JSONData{

    private String temperatureLow;

    @Override
    public void retrieveData(JSONObject data) {
        temperatureLow = data.optString("fahrenheit");
    }

    public String getTemperatureLow() {
        return temperatureLow;
    }
}
