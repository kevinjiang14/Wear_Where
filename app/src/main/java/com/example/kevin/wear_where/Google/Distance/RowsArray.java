package com.example.kevin.wear_where.Google.Distance;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Hermes on 10/23/2016.
 */

public class RowsArray implements JSONArrayData {

    private ElementsArray elementsArray;

    @Override
    public void retrieveData(JSONArray data) {
        try {
            elementsArray = new ElementsArray();
            JSONArray tempArray =  data.getJSONObject(0).getJSONArray("elements");

            elementsArray.retrieveData(tempArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ElementsArray getElementsArray() {
        return elementsArray;
    }
}
