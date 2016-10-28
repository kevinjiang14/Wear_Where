package com.example.kevin.wear_where.WundergroundData.Planner.TripData.TemperatureData;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/20/16.
 */

public class AverageTemp implements JSONData {

    private int fahrenheit;
    private int celsius;

    @Override
    public void retrieveData(JSONObject data) {
        fahrenheit = data.optInt("F");
        celsius = data.optInt("C");
    }

    public int getFahrenheit(){
        return fahrenheit;
    }

    public int getCelsius(){
        return celsius;
    }
}
