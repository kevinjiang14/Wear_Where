package com.example.kevin.wear_where.WundergroundData.HourlyForecast;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/16/16.
 */
public class Temperature implements JSONData {

    private int temperature;

    public Temperature() {

    }

    public Temperature (int temp) {
        this.temperature = temp;
    }

    @Override
    public void retrieveData(JSONObject data) {
        temperature = data.optInt("english");
    }


    public int getTemperature(){
        return temperature;
    }
}
