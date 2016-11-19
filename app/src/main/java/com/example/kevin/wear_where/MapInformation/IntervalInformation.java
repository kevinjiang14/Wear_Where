package com.example.kevin.wear_where.MapInformation;

import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Hermes on 11/19/2016.
 */

public class IntervalInformation {
    public final String intervalTitle;
    public final String intervalDetails;

    public IntervalInformation(String intervalTitle, String intervalDetail) {
        this.intervalTitle = intervalTitle;
        this.intervalDetails = intervalDetail;
    }
}
