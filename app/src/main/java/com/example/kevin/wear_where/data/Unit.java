package com.example.kevin.wear_where.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/11/2016.
 */
public class Unit implements JSONData {

    private String temperatureUnit;

    @Override
    public void retrieveData(JSONObject data) {
        temperatureUnit = data.optString("temperature");
    }

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("temperature", temperatureUnit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getTemperatureUnit(){
        return temperatureUnit;
    }
}
