package com.example.kevin.wear_where.WundergroundData.HourlyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/16/16.
 */
public class FCTtime implements JSONData {

    private int hour;
    private String ampm;


    @Override
    public void retrieveData(JSONObject data) {
        if(data.optInt("hour") <= 12 && data.optInt("hour") >= 1) {
            hour = data.optInt("hour");
        }
        else if(data.optInt("hour") == 0){
            hour = data.optInt("hour") + 12;
        }
        else hour = data.optInt("hour") - 12;

        ampm = data.optString("ampm");
    }

    public int getHour(){
        return hour;
    }

    public String getAmpm(){
        return ampm;
    }
}
