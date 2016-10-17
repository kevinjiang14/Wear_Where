package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONArrayData;
import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class LegsArray implements JSONArrayData {
    private Distance distance;
    private Duration duration;
    private StepsArray stepsArray;

    @Override
    public void retrieveData(JSONArray data) {

        try {
            distance = new Distance();
            JSONObject tempObject =  data.getJSONObject(0).getJSONObject("distance");

            distance.retrieveData(tempObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            duration = new Duration();
            JSONObject tempObject =  data.getJSONObject(0).getJSONObject("duration");

            duration.retrieveData(tempObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            stepsArray = new StepsArray();
            JSONArray tempArray = data.getJSONObject(0).getJSONArray("steps");

            stepsArray.retrieveData(tempArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public StepsArray getStepsArray() {
        return stepsArray;
    }
}
