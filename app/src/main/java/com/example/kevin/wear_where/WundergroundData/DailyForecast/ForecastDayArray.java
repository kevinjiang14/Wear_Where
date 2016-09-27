package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kevin Jiang on 9/21/16.
 */

public class ForecastDayArray implements JSONArrayData {

    private ArrayList<ForecastDayItem> forecastItemArray;


    @Override
    public void retrieveData(JSONArray data) {
        forecastItemArray = new ArrayList<>();
        for(int i = 0; i < data.length(); i++){
            try {
                ForecastDayItem forecastItem = new ForecastDayItem();
                JSONObject temp = data.getJSONObject(i);

                forecastItem.retrieveData(temp);

                forecastItemArray.add(forecastItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<ForecastDayItem> getForecastItemArray() {
        return forecastItemArray;
    }
}
