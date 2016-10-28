package com.example.kevin.wear_where.Google.Directions;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hermes on 10/16/2016.
 */

public class DirectionsObject {
    private JSONObject baseObject;
    private RoutesArray routesArray;

    public DirectionsObject (StringBuilder results) {
        routesArray = new RoutesArray();
        try {
            baseObject = new JSONObject(results.toString());
            routesArray.retrieveData(baseObject.getJSONArray("routes"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBaseObject() {
        return baseObject;
    }

    public RoutesArray getRoutesArray() {
        return routesArray;
    }
}
