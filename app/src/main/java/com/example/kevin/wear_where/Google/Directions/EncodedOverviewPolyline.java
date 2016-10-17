package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class EncodedOverviewPolyline implements JSONData {
    private String encodedOverviewPolyline;

    @Override
    public void retrieveData(JSONObject data) {
        encodedOverviewPolyline = data.optString("points");
    }

    public String getEncodedOverviewPolyline() {
        return encodedOverviewPolyline;
    }
}
