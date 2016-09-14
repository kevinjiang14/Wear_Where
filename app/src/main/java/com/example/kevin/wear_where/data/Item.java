package com.example.kevin.wear_where.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/11/2016.
 */
public class Item implements JSONData {

    private Condition condition;

    @Override
    public void retrieveData(JSONObject data) {
        condition = new Condition();
        condition.retrieveData(data.optJSONObject("condition"));
    }

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("condition", condition.toJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Condition getCondition(){
        return condition;
    }
}
