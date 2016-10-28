package com.example.kevin.wear_where.Google.Distance;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hermes on 10/23/2016.
 */

public class DistanceMatrixObject {
    private JSONObject baseObject;
    private RowsArray rowsArray;
    private StartingAddressesArray startingAddressesArray;
    private DestinationAddressesArray destinationAddressesArray;

    public DistanceMatrixObject (StringBuilder results) {
        rowsArray = new RowsArray();
        try {
            baseObject = new JSONObject(results.toString());
            rowsArray.retrieveData(baseObject.getJSONArray("rows"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        destinationAddressesArray = new DestinationAddressesArray();
        try {
            destinationAddressesArray.retrieveData(baseObject.getJSONArray("destination_addresses"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        startingAddressesArray = new StartingAddressesArray();
        try {
            startingAddressesArray.retrieveData(baseObject.getJSONArray("origin_addresses"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBaseObject() {
        return baseObject;
    }

    public RowsArray getRowsArray() {
        return rowsArray;
    }

    public DestinationAddressesArray getDestinationAddressesArray() {
        return destinationAddressesArray;
    }

    public StartingAddressesArray getStartingAddressesArray() {
        return startingAddressesArray;
    }
}