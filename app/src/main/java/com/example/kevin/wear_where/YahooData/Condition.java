package com.example.kevin.wear_where.YahooData;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/11/2016.
 */
public class Condition implements JSONData {

    private int code;
    private int temperature;
    private String description;

    @Override
    public void retrieveData(JSONObject data) {
        code = data.optInt("code");

        temperature = data.optInt("temp");

        description = data.optString("text");
    }

    public int getCode(){
        return code;
    }

    public int getTemperature(){
        return temperature;
    }

    public String getDescription(){
        return description;
    }
}