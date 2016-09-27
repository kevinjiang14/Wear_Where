package com.example.kevin.wear_where.WundergroundData.CurrentCondition;

import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/20/16.
 */

public class CurrentObservation implements JSONData{

    private Image image;
    private DisplayLocation disLocation;
    private String tempf;
    private String conditionURL;
    private String condition;
    private String humidity;

    @Override
    public void retrieveData(JSONObject data) {
        image = new Image();
        image.retrieveData(data.optJSONObject("image"));

        disLocation = new DisplayLocation();
        disLocation.retrieveData(data.optJSONObject("display_location"));

        tempf = data.optString("temp_f");
        condition = data.optString("weather");
        conditionURL = data.optString("icon_url");
        humidity = data.optString("relative_humidity");
    }

    public String getConditionURL() {
        return conditionURL;
    }

    public String getCondition() {
        return condition;
    }

    public String getTempf() {
        return tempf;
    }

    public DisplayLocation getDisLocation() {
        return disLocation;
    }

    public Image getImage() {
        return image;
    }

    public String getHumidity() {
        return humidity;
    }
}
