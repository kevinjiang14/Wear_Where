package com.example.kevin.wear_where.WundergroundData.CurrentCondition;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/20/16.
 */

public class ConditionsObject {

    private JSONObject baseObject;
    private CurrentObservation curObs;

    public ConditionsObject(StringBuilder results) {
        curObs = new CurrentObservation();
        try {
            baseObject = new JSONObject(results.toString());

            curObs.retrieveData(baseObject.optJSONObject("current_observation"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBaseObject() {
        return baseObject;
    }

    public CurrentObservation getCurrentObservation() {
        return curObs;
    }

    public String getTemperature(){
        return curObs.getTempf();
    }

    public String getHumidity(){
        return curObs.getHumidity();
    }

    public String getCondition(){
        return curObs.getCondition();
    }

    public String getConditionImageLink(){
        return curObs.getConditionURL();
    }
}
