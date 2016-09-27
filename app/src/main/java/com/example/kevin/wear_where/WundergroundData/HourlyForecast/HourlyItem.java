package com.example.kevin.wear_where.WundergroundData.HourlyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/19/16.
 */
public class HourlyItem implements JSONData{


    private FCTtime hourly;
    private Temperature temperature;
    private String condition;
    private String iconURL;

    @Override
    public void retrieveData(JSONObject data) {
        hourly = new FCTtime();
        hourly.retrieveData(data.optJSONObject("FCTTIME"));

        temperature = new Temperature();
        temperature.retrieveData(data.optJSONObject("temp"));

        condition = data.optString("condition");

        iconURL = data.optString("icon_url");
    }

    public FCTtime getFCTtime(){
        return hourly;
    }

    public Temperature getTemperature(){
        return temperature;
    }

    public String getCondition(){
        return condition;
    }

    public String getIconURL(){
        return iconURL;
    }
}
