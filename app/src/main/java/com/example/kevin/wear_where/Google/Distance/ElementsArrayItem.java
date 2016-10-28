package com.example.kevin.wear_where.Google.Distance;

import com.example.kevin.wear_where.Google.Directions.Distance;
import com.example.kevin.wear_where.Google.Directions.Duration;
import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Hermes on 10/23/2016.
 */

public class ElementsArrayItem implements JSONData {
    private Distance distance;
    private Duration duration;

    @Override
    public void retrieveData(JSONObject data) {
        distance = new Distance();
        distance.retrieveData(data.optJSONObject("distance"));

        duration = new Duration();
        duration.retrieveData(data.optJSONObject("duration"));
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }
}

