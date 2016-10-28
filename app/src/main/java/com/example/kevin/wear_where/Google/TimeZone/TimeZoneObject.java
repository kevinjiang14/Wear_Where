package com.example.kevin.wear_where.Google.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hermes on 10/24/2016.
 */

public class TimeZoneObject {
    private JSONObject baseObject;
    private String dstOffset;
    private String rawOffset;
    private String timeZoneId;
    private String timeZoneName;
    private String status;

    public TimeZoneObject (StringBuilder results) {
        try {
            baseObject = new JSONObject(results.toString());
            dstOffset = baseObject.optString("dstOffset");
            rawOffset = baseObject.optString("rawOffset");
            timeZoneId = baseObject.optString("timeZoneId");
            timeZoneName = baseObject.optString("timeZoneName");
            status = baseObject.optString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getBaseObject() {
        return baseObject;
    }

    public String getDstOffset() {
        return dstOffset;
    }

    public String getRawOffset() {
        return rawOffset;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public String getStatus() {
        return status;
    }
}
