package com.example.kevin.wear_where.Google.Distance;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hermes on 10/23/2016.
 */

public class ElementsArray implements JSONArrayData {
    private ArrayList<ElementsArrayItem> elementsArrayItems;

    @Override
    public void retrieveData(JSONArray data) {
        elementsArrayItems = new ArrayList<>();
        for(int i = 0; i < data.length(); i++){
            try {
                ElementsArrayItem elementsArrayItem = new ElementsArrayItem();
                JSONObject temp = data.getJSONObject(i);

                elementsArrayItem.retrieveData(temp);

                elementsArrayItems.add(elementsArrayItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<ElementsArrayItem> getElementsArrayItems() {
        return elementsArrayItems;
    }
}

