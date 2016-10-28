package com.example.kevin.wear_where.WundergroundData.Planner.TripData;

import com.example.kevin.wear_where.Interface.JSONData;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.BelowFreezing;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Cloudy;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Fog;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Freezing;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Hail;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Humid;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.PartlyCloudy;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Precipitation;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Rain;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Snow;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.SnowonGround;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Sultry;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.SunnyCloudy;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.TempOverNinety;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.TempOverSixty;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Thunder;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Tornado;
import com.example.kevin.wear_where.WundergroundData.Planner.TripData.ConditionPercent.Windy;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 10/20/16.
 */

public class Chance implements JSONData {

    private BelowFreezing belowFreezing;
    private Cloudy cloudy;
    private Fog fog;
    private Freezing freezing;
    private Hail hail;
    private Humid humid;
    private PartlyCloudy partlyCloudy;
    private Precipitation precipitation;
    private Rain rain;
    private Snow snow;
    private SnowonGround snowonGround;
    private Sultry sultry;
    private SunnyCloudy sunnyCloudy;
    private TempOverNinety tempOverNinety;
    private TempOverSixty tempOverSixty;
    private Thunder thunder;
    private Tornado tornado;
    private Windy windy;

    @Override
    public void retrieveData(JSONObject data) {
        belowFreezing = new BelowFreezing();
        cloudy = new Cloudy();
        fog = new Fog();
        freezing = new Freezing();
        hail = new Hail();
        humid = new Humid();
        partlyCloudy = new PartlyCloudy();
        precipitation = new Precipitation();
        rain = new Rain();
        snow = new Snow();
        snowonGround = new SnowonGround();
        sultry = new Sultry();
        sunnyCloudy = new SunnyCloudy();
        tempOverNinety = new TempOverNinety();
        tempOverSixty = new TempOverSixty();
        thunder = new Thunder();
        tornado = new Tornado();
        windy = new Windy();

        belowFreezing.retrieveData(data.optJSONObject("tempbelowfreezing"));
        cloudy.retrieveData(data.optJSONObject("chanceofcloudyday"));
        fog.retrieveData(data.optJSONObject("chanceoffogday"));
        freezing.retrieveData(data.optJSONObject("tempoverfreezing"));
        hail.retrieveData(data.optJSONObject("chanceofhailday"));
        humid.retrieveData(data.optJSONObject("chanceofhumidday"));
        partlyCloudy.retrieveData(data.optJSONObject("chanceofpartlycloudyday"));
        precipitation.retrieveData(data.optJSONObject("chanceofprecip"));
        rain.retrieveData(data.optJSONObject("chanceofrainday"));
        snow.retrieveData(data.optJSONObject("chanceofsnowday"));
        snowonGround.retrieveData(data.optJSONObject("chanceofsnowonground"));
        sultry.retrieveData(data.optJSONObject("chanceofsultryday"));
        sunnyCloudy.retrieveData(data.optJSONObject("chanceofsunnycloudyday"));
        tempOverNinety.retrieveData(data.optJSONObject("tempoverninety"));
        tempOverSixty.retrieveData(data.optJSONObject("tempoversixty"));
        thunder.retrieveData(data.optJSONObject("chanceofthunderday"));
        tornado.retrieveData(data.optJSONObject("chanceoftornadoday"));
        windy.retrieveData(data.optJSONObject("chanceofwindyday"));
    }

    public int getBelowFreezingPercent(){
        return belowFreezing.getPercentage();
    }

    public int getCloudyPercent(){
        return cloudy.getPercentage();
    }

    public int getFogPercent(){
        return fog.getPercentage();
    }

    public int getFreezingPercent(){
        return freezing.getPercentage();
    }

    public int getHailPercent(){
        return hail.getPercentage();
    }

    public int getHumidPercent(){
        return humid.getPercentage();
    }

    public int getPartlyCloudyPercent(){
        return partlyCloudy.getPercentage();
    }

    public int getPrecipitationPercent(){
        return precipitation.getPercentage();
    }

    public int getRainPercent(){
        return rain.getPercentage();
    }

    public int getSnowPercent(){
        return snow.getPercentage();
    }

    public int getSnowOnGroundPercent(){
        return snowonGround.getPercentage();
    }

    public int getSultryPercent(){
        return sultry.getPercentage();
    }

    public int getSunnyCloudyPercent(){
        return sunnyCloudy.getPercentage();
    }

    public int getTempOverNinetyPercent(){
        return tempOverNinety.getPercentage();
    }

    public int getTempOverSixtyPercent(){
        return tempOverSixty.getPercentage();
    }

    public int getThunderPercent(){
        return thunder.getPercentage();
    }

    public int getTornadoPercent(){
        return tornado.getPercentage();
    }

    public int getWindyPercent(){
        return windy.getPercentage();
    }

    public BelowFreezing getBelowFreezing(){
        return belowFreezing;
    }

    public Cloudy getCloudy(){
        return cloudy;
    }

    public Fog getFog(){
        return fog;
    }

    public Freezing getFreezing(){
        return freezing;
    }

    public Hail getHail(){
        return hail;
    }

    public Humid getHumid(){
        return humid;
    }

    public PartlyCloudy getPartlyCloudy(){
        return partlyCloudy;
    }

    public Precipitation getPrecipitation(){
        return precipitation;
    }

    public Rain getRain(){
        return rain;
    }

    public Snow getSnow(){
        return snow;
    }

    public SnowonGround getSnowonGround(){
        return snowonGround;
    }

    public Sultry getSultry(){
        return sultry;
    }

    public SunnyCloudy getSunnyCloudy(){
        return sunnyCloudy;
    }

    public TempOverNinety getTempOverNinety(){
        return tempOverNinety;
    }

    public TempOverSixty getTempOverSixty(){
        return tempOverSixty;
    }

    public Thunder getThunder(){
        return thunder;
    }

    public Tornado getTornado(){
        return tornado;
    }

    public Windy getWindy(){
        return windy;
    }
}
