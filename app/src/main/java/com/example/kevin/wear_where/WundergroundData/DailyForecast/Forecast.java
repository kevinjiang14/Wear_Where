package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/21/16.
 */

public class Forecast implements JSONData {

    private SimpleForecast forecastSimple;

    @Override
    public void retrieveData(JSONObject data) {
        forecastSimple = new SimpleForecast();
        forecastSimple.retrieveData(data.optJSONObject("simpleforecast"));
    }

    public SimpleForecast getForecastSimple() {
        return forecastSimple;
    }
}
