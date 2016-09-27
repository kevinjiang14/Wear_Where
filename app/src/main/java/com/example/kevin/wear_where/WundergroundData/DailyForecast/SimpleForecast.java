package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/21/16.
 */

public class SimpleForecast implements JSONData {

    private ForecastDayArray forecastArray;

    @Override
    public void retrieveData(JSONObject data) {
        forecastArray = new ForecastDayArray();
        try {
            forecastArray.retrieveData(data.getJSONArray("forecastday"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ForecastDayArray getForecastArray() {
        return forecastArray;
    }
}
