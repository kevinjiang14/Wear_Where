package com.example.kevin.wear_where.WundergroundData.DailyForecast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/21/16.
 */

public class DailyObject {

    private JSONObject baseObject;
    private Forecast forecast;

    public DailyObject(StringBuilder results) {
        forecast = new Forecast();
        try {
            baseObject = new JSONObject(results.toString());

            forecast.retrieveData(baseObject.optJSONObject("forecast"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBaseObject() {
        return baseObject;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public String getDate(int index){
        return forecast.getForecastSimple().getForecastArray().getForecastItemArray().get(index).getDate().getWeekday();
    }

    public int getHigh(int index){
        return forecast.getForecastSimple().getForecastArray().getForecastItemArray().get(index).getDailyHigh().getTemperatureHigh();
    }

    public int getLow(int index){
        return forecast.getForecastSimple().getForecastArray().getForecastItemArray().get(index).getDailyLow().getTemperatureLow();
    }

    public String getCondition(int index){
        return forecast.getForecastSimple().getForecastArray().getForecastItemArray().get(index).getCondition();
    }

    public String getIconURL(int index){
        return forecast.getForecastSimple().getForecastArray().getForecastItemArray().get(index).getIconURL();
    }
}
