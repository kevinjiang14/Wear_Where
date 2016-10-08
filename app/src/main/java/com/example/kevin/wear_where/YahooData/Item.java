package com.example.kevin.wear_where.YahooData;

import com.example.kevin.wear_where.Interface.JSONData;

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

    public Condition getCondition(){
        return condition;
    }
}
