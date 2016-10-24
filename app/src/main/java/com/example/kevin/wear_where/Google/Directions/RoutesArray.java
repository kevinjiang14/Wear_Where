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
        legsArray = new LegsArray();
        try {
            JSONArray tempArray =  data.getJSONObject(0).getJSONArray("legs");
            legsArray.retrieveData(tempArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        encodedOverviewPolyline = new EncodedOverviewPolyline();
        try {
            JSONObject tempObject = data.getJSONObject(0).getJSONObject("overview_polyline");
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
