package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class EncodedPolyline implements JSONData {
    private String encodedPolyline;

    @Override
    public void retrieveData(JSONObject data) {
        encodedPolyline = data.optString("points");
    }

    public String getEncodedPolyline() {
        return encodedPolyline;
    }
}
