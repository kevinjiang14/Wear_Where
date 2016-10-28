package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.List;

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

    public List<LatLng> getPolyline() {
        return com.google.maps.android.PolyUtil.decode(encodedPolyline);
    }

}
