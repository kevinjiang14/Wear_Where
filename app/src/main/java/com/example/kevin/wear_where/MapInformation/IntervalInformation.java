package com.example.kevin.wear_where.MapInformation;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Hermes on 11/19/2016.
 */

public class IntervalInformation {
    public final String intervalTitle;
    public final String intervalDetail;
    public final String intervalTemperature;

    public IntervalInformation(String intervalTitle, String intervalDetail, String intervalTemperature) {
        this.intervalTitle = intervalTitle;
        this.intervalDetail = intervalDetail;
        this.intervalTemperature = intervalTemperature;
    }
}
