package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/27/16.
 */

public class DailyHigh implements JSONData{

    private String temperatureHigh;

    @Override
    public void retrieveData(JSONObject data) {
        temperatureHigh = data.optString("fahrenheit");
    }

    public String getTemperatureHigh() {
        return temperatureHigh;
    }
}
