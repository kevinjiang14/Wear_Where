package com.example.kevin.wear_where.WundergroundData.Planner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/19/16.
 */

public class PlannerObject {

    private JSONObject plannerObject;
    private Trip tripObject;

    public PlannerObject(StringBuilder results) {
        tripObject = new Trip();

        try {
            plannerObject = new JSONObject(results.toString());

            tripObject.retrieveData(plannerObject.optJSONObject("trip"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getTempHighMin(){
        return tripObject.getTempHighMin();
    }

    public int getTempHighMax(){
        return tripObject.getTempHighMax();
    }

    public int getTempHighAvg(){
        return tripObject.getTempHighAvg();
    }

    public int getTempLowMin(){
        return tripObject.getTempLowMin();
    }

    public int getTempLowMax(){
        return tripObject.getTempLowMax();
    }

    public int getTempLowAvg(){
        return tripObject.getTempLowAvg();
    }

    public int getBelowFreezingChance(){
        return tripObject.getBelowFreezingChance();
    }

    public int getCloudyChance(){
        return tripObject.getCloudyChance();
    }

    public int getFogChance(){
        return tripObject.getFogChance();
    }

    public int getFreezingChance(){
        return tripObject.getFreezingChance();
    }

    public int getHailChance(){
        return tripObject.getHailChance();
    }

    public int getHumidChance(){
        return tripObject.getHumidChance();
    }

    public int getPartlyCloudyChance(){
        return tripObject.getPartlyCloudyChance();
    }

    public int getPrecipitationChance(){
        return tripObject.getPrecipitationChance();
    }

    public int getRainChance(){
        return tripObject.getRainChance();
    }

    public int getSnowChance(){
        return tripObject.getSnowChance();
    }

    public int getSnowOnGroundChance(){
        return tripObject.getSnowOnGroundChance();
    }

    public int getSultryChance(){
        return tripObject.getSultryChance();
    }

    public int getSunnyCloudyChance(){
        return tripObject.getSunnyCloudyChance();
    }

    public int getTempOverNinetyChance(){
        return tripObject.getTempOverNinetyChance();
    }

    public int getTempOverSixtyChance(){
        return tripObject.getTempOverSixtyChance();
    }

    public int getThunderChance(){
        return tripObject.getThunderChance();
    }

    public int getTornadoChance(){
        return tripObject.getTornadoChance();
    }

    public int getWindyChance(){
        return tripObject.getWindyChance();
    }
}
