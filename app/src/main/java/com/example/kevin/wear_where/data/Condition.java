package com.example.kevin.wear_where.data;

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

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("code", code);
            data.put("temp", temperature);
            data.put("tezt", description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
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