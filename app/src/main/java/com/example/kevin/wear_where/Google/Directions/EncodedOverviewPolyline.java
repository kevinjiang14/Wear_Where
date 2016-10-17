package com.example.kevin.wear_where.Google.Directions;

import com.example.kevin.wear_where.Interface.JSONData;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.List;

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

    public List<LatLng> getOverviewPolyline() {
        return com.google.maps.android.PolyUtil.decode(encodedOverviewPolyline);
    }
}
