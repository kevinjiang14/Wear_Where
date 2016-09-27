package com.example.kevin.wear_where.YahooData;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/11/2016.
 */
public class Channel implements JSONData {

    private Unit units;
    private Item item;
    private String location;

    private String temp;
    private String condition;

    @Override
    public void retrieveData(JSONObject data) {
        units = new Unit();
        units.retrieveData(data.optJSONObject("units"));

        item = new Item();
        item.retrieveData(data.optJSONObject("item"));

        JSONObject locationData = data.optJSONObject("location");

        String city = locationData.optString("city");

        String region = locationData.optString("region");

        location = city + ", " + region;
        // Debugger
        //Log.d("WearWhere", "" + location);
    }

    public Unit getUnits(){
        return units;
    }

    public Item getItem(){
        return item;
    }

    public String getLocation(){
        return location;
    }

    public String getTemp(){
        return temp;
    }

    public String getCondition(){
        return condition;
    }

    public void setTemp(String value){
        temp = value;
    }

    public void setCondition(String value){
        condition = value;
    }
}