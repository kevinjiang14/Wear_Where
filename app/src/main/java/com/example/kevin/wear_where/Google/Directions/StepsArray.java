package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hermes on 10/16/2016.
 */

public class StepsArray implements JSONArrayData {
    private ArrayList<StepsArrayItem> stepsArrayItems;

    @Override
    public void retrieveData(JSONArray data) {
        stepsArrayItems = new ArrayList<>();
        for(int i = 0; i < data.length(); i++){
            try {
                StepsArrayItem stepsArrayItem = new StepsArrayItem();
                JSONObject temp = data.getJSONObject(i);

                stepsArrayItem.retrieveData(temp);

                stepsArrayItems.add(stepsArrayItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<StepsArrayItem> getStepsArrayItems() {
        return stepsArrayItems;
    }
}
