package com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/20/16.
 */

public class Freezing implements JSONData {
    private String name;
    private String description;
    private int percentage;

    @Override
    public void retrieveData(JSONObject data) {
        name = data.optString("name");
        description = data.optString("desciption");
        percentage = data.optInt("percentage");
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getPercentage(){
        return percentage;
    }
}
