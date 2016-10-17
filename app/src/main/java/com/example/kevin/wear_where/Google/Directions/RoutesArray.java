package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONArrayData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hermes on 10/16/2016.
 */

public class RoutesArray implements JSONArrayData {

    private LegsArray legsArray;
    private EncodedOverviewPolyline encodedOverviewPolyline;

    @Override
    public void retrieveData(JSONArray data) {

        try {
            legsArray = new LegsArray();
            JSONArray tempArray = data.getJSONArray(2);

            legsArray.retrieveData(tempArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            encodedOverviewPolyline = new EncodedOverviewPolyline();
            JSONObject tempObject = data.getJSONObject(3);

            encodedOverviewPolyline.retrieveData(tempObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public LegsArray getLegsArray() {
        return legsArray;
    }

    public EncodedOverviewPolyline getEncodedOverviewPolyLine() {
        return encodedOverviewPolyline;
    }
}
