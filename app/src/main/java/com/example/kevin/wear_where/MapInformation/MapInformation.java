package com.example.kevin.wear_where.MapInformation;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Hermes on 11/19/2016.
 */

public class MapInformation {
    public final ArrayList<MarkerOptions> intervalInformation;
    public final ArrayList<String> intervalTitles;
    public final ArrayList<String> intervalDetails;
    public final ArrayList<String> intervalTemperatures;

    public MapInformation(ArrayList<MarkerOptions> intervalInformation, ArrayList<String> intervalTitles, ArrayList<String> intervalDetails, ArrayList<String> intervalTemperatures) {
        this.intervalInformation = intervalInformation;
        this.intervalTitles = intervalTitles;
        this.intervalDetails = intervalDetails;
        this.intervalTemperatures = intervalTemperatures;
    }
}
