package com.example.kevin.wear_where.WundergroundData.Planner.TripData;

import com.example.kevin.wear_where.Interface.JSONData;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.TemperatureData.AverageTemp;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.TemperatureData.MaxTemp;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.TemperatureData.MinTemp;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/20/16.
 */

public class TempHigh implements JSONData {

    private MinTemp min;
    private MaxTemp max;
    private AverageTemp avg;

    @Override
    public void retrieveData(JSONObject data) {
        min = new MinTemp();
        max = new MaxTemp();
        avg = new AverageTemp();

        min.retrieveData(data.optJSONObject("min"));
        max.retrieveData(data.optJSONObject("max"));
        avg.retrieveData(data.optJSONObject("avg"));
    }

    public int getMinFahrenheit(){
        return min.getFahrenheit();
    }

    public int getMaxFahrenheit(){
        return max.getFahrenheit();
    }

    public int getAvgFahrenheit(){
        return avg.getFahrenheit();
    }

    public MinTemp getMinTemp(){
        return min;
    }

    public MaxTemp getMaxTemp(){
        return max;
    }

    public AverageTemp getAvgTemp(){
        return avg;
    }
}
