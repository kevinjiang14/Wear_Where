package com.example.kevin.wear_where.WundergroundData.HourlyForecast;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kevin Jiang on 9/16/16.
 */
public class HourlyForecastArray implements JSONArrayData {

    private ArrayList<HourlyItem> FCTtimeArray;

    @Override
    public void retrieveData(JSONArray data) {
        FCTtimeArray = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            try {
                HourlyItem FCTtemp = new HourlyItem();
                JSONObject temp = data.getJSONObject(i);


                FCTtemp.retrieveData(temp);

                FCTtimeArray.add(FCTtemp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int getLength(){
        return FCTtimeArray.size();
    }

    public ArrayList<HourlyItem> getFCTtimeArray(){
        return FCTtimeArray;
    }
}
