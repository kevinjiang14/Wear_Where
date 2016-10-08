package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/27/16.
 */

public class Date implements JSONData{

    private String weekday;

    @Override
    public void retrieveData(JSONObject data) {
        weekday = data.optString("weekday_short");
    }

    public String getWeekday() {
        return weekday;
    }
}
