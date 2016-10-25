package com.example.kevin.wear_where.Google.Distance;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Hermes on 10/23/2016.
 */

public class StartingAddressesArray implements JSONArrayData {
    private ArrayList<String> startingAddressesArrayItems;

    @Override
    public void retrieveData(JSONArray data) {
        startingAddressesArrayItems = new ArrayList<>();
        for(int i = 0; i < data.length(); i++){
            try {
                startingAddressesArrayItems.add(data.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getStartingAddressesArrayItems() {
        return startingAddressesArrayItems;
    }
}
