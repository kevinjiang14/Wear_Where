package com.example.kevin.wear_where.Google.Distance;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Hermes on 10/23/2016.
 */

public class DestinationAddressesArray implements JSONArrayData {
    private ArrayList<String> destinationAddressesArrayItems;

    @Override
    public void retrieveData(JSONArray data) {
        destinationAddressesArrayItems = new ArrayList<>();
        for(int i = 0; i < data.length(); i++){
            try {
                destinationAddressesArrayItems.add(data.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getDestinationAddressesArrayItems() {
        return destinationAddressesArrayItems;
    }
}
