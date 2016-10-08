package com.example.kevin.wear_where.WundergroundData.HourlyForecast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/20/16.
 */

public class HourlyObject {

    private JSONObject baseObject;
    private HourlyForecastArray HFarray;

    public HourlyObject(StringBuilder results) {
        HFarray = new HourlyForecastArray();
        try {
            baseObject = new JSONObject(results.toString());

            HFarray.retrieveData(baseObject.optJSONArray("hourly_forecast"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBaseObject() {
        return baseObject;
    }

    public HourlyForecastArray getHFarray() {
        return HFarray;
    }

    public HourlyItem getHourly(int index){
        return HFarray.getFCTtimeArray().get(index);
    }

    public int getTemperatureF(int index){
        return getHourly(index).getTemperature().getTemperature();
    }

    public int getHour(int index){
        return getHourly(index).getFCTtime().getHour();
    }

    public String getAMPM(int index){
        return getHourly(index).getFCTtime().getAmpm();
    }

    public String getCondition(int index){
        return getHourly(index).getCondition();
    }

    public String getIconURL(int index){
        return getHourly(index).getIconURL();
    }
}
