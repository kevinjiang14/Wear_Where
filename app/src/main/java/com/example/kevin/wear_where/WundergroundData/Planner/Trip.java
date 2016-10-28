package com.example.kevin.wear_where.WundergroundData.Planner;

import com.example.kevin.wear_where.Interface.JSONData;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.Chance;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.TempHigh;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.TempLow;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/20/16.
 */

public class Trip implements JSONData{

    private TempHigh tempHigh;
    private TempLow tempLow;
    private Chance chance;

    @Override
    public void retrieveData(JSONObject data) {
        tempHigh = new TempHigh();
        tempLow = new TempLow();
        chance = new Chance();

        tempHigh.retrieveData(data.optJSONObject("temp_high"));
        tempLow.retrieveData(data.optJSONObject("temp_low"));
        chance.retrieveData(data.optJSONObject("chance_of"));
    }

    public int getTempHighMin(){
        return tempHigh.getMinFahrenheit();
    }

    public int getTempHighMax(){
        return tempHigh.getMaxFahrenheit();
    }

    public int getTempHighAvg(){
        return tempHigh.getAvgFahrenheit();
    }

    public int getTempLowMin(){
        return tempLow.getMinFahrenheit();
    }

    public int getTempLowMax(){
        return tempLow.getMaxFahrenheit();
    }

    public int getTempLowAvg(){
        return tempLow.getAvgFahrenheit();
    }

    public int getBelowFreezingChance(){
        return chance.getBelowFreezingPercent();
    }

    public int getCloudyChance(){
        return chance.getCloudyPercent();
    }

    public int getFogChance(){
        return chance.getFogPercent();
    }

    public int getFreezingChance(){
        return chance.getFreezingPercent();
    }

    public int getHailChance(){
        return chance.getHailPercent();
    }

    public int getHumidChance(){
        return chance.getHumidPercent();
    }

    public int getPartlyCloudyChance(){
        return chance.getPartlyCloudyPercent();
    }

    public int getPrecipitationChance(){
        return chance.getPrecipitationPercent();
    }

    public int getRainChance(){
        return chance.getRainPercent();
    }

    public int getSnowChance(){
        return chance.getSnowPercent();
    }

    public int getSnowOnGroundChance(){
        return chance.getSnowOnGroundPercent();
    }

    public int getSultryChance(){
        return chance.getSultryPercent();
    }

    public int getSunnyCloudyChance(){
        return chance.getSunnyCloudyPercent();
    }

    public int getTempOverNinetyChance(){
        return chance.getTempOverNinetyPercent();
    }

    public int getTempOverSixtyChance(){
        return chance.getTempOverSixtyPercent();
    }

    public int getThunderChance(){
        return chance.getThunderPercent();
    }

    public int getTornadoChance(){
        return chance.getTornadoPercent();
    }

    public int getWindyChance(){
        return chance.getWindyPercent();
    }
}
