package com.example.kevin.wear_where.WundergroundData.Geolookup;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/21/16.
 */

public class GeoLookupObject {

    private JSONObject baseObject;
    private Location location;

    public GeoLookupObject(StringBuilder results) {
        location = new Location();
        try {
            baseObject = new JSONObject(results.toString());

            location.retrieveData(baseObject.optJSONObject("location"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCity(){
        return location.getCity();
    }

    public String getState(){
        return location.getState();
    }
}
