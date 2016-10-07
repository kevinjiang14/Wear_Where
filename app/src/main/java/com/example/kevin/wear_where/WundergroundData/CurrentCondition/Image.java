package com.example.kevin.wear_where.WundergroundData.CurrentCondition;

import android.graphics.Bitmap;

import com.example.kevin.wear_where.AsyncTask.ImageLoaderAST;
import com.example.kevin.wear_where.Interface.JSONData;

import org.json.JSONObject;

/**
 * Created by Kevin Jiang on 9/20/16.
 */

public class Image implements JSONData{

    private String iconURL;

    public String getIconURL() {
        return iconURL;
    }

    @Override
    public void retrieveData(JSONObject data) {
        iconURL = data.optString("url");
    }
}
