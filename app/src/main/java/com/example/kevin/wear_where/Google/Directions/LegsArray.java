package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONArrayData;

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
            JSONObject tempObject = data.getJSONObject(0);

            distance.retrieveData(tempObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            duration = new Duration();
            JSONObject tempObject = data.getJSONObject(1);

            duration.retrieveData(tempObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            stepsArray = new StepsArray();
            JSONArray tempArray = data.getJSONArray(6);

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
