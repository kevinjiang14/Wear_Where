package com.example.kevin.wear_where.data;

/**
 * Created by Kevin Jiang on 9/11/2016.
 */

import org.json.JSONObject;

public interface JSONData {
    void retrieveData(JSONObject data);

    JSONObject toJSON();
}
