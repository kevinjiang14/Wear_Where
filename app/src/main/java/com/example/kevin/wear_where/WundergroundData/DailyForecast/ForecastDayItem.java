package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/21/16.
 */

public class ForecastDayItem implements JSONData {

    private Date date;
    private DailyHigh dailyHigh;
    private DailyLow dailyLow;

    private String condition;
    private String iconURL;

    @Override
    public void retrieveData(JSONObject data) {
        date = new Date();
        date.retrieveData(data.optJSONObject("date"));

        dailyHigh = new DailyHigh();
        dailyHigh.retrieveData(data.optJSONObject("high"));

        dailyLow = new DailyLow();
        dailyLow.retrieveData(data.optJSONObject("low"));

        condition = data.optString("conditions");

        iconURL = data.optString("icon_url");
    }

    public Date getDate() {
        return date;
    }

    public DailyHigh getDailyHigh() {
        return dailyHigh;
    }

    public DailyLow getDailyLow() {
        return dailyLow;
    }

    public String getCondition() {
        return condition;
    }

    public String getIconURL() {
        return iconURL;
    }
}
