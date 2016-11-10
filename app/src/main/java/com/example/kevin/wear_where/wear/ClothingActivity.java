package com.example.kevin.wear_where.wear;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kevin.wear_where.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.kevin.wear_where.MainActivity.MESSAGE;
import static com.example.kevin.wear_where.VacationDataActivity.HEADWEAR;
import static com.example.kevin.wear_where.VacationDataActivity.LOWERBODY;
import static com.example.kevin.wear_where.VacationDataActivity.SHOES;
import static com.example.kevin.wear_where.VacationDataActivity.UPPERBODY;

/**
 * Created by Calvin on 10/26/2016.
 */

public class ClothingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothing_list);

        ListView list = (ListView)findViewById(R.id.list);
        Intent intent = getIntent();
        if(intent.getStringArrayExtra(MESSAGE) != null) {
            String[] clothingList = intent.getStringArrayExtra(MESSAGE);
            list.setAdapter(new ArrayAdapter<>(this, R.layout.mylist, R.id.itemname, clothingList));
        }
        else {
            Spanned[] clothingList = CreateVacationSuggestionList();
            list.setAdapter(new ArrayAdapter<>(this, R.layout.mylist, R.id.itemname, clothingList));
        }
    }

    public Spanned[] CreateVacationSuggestionList(){
        ArrayList<Spanned> tempList = new ArrayList<>();
        Bundle bundle;
        String[] list;

        // Add Headwear items to arraylist
        tempList.add(Html.fromHtml("<b>HEADWEAR</b>"));
        bundle = getIntent().getBundleExtra(HEADWEAR + ".Bundle");
        if(bundle.get(HEADWEAR + ".Hot") != null){
            tempList.add(Html.fromHtml("<b>Hot</b>"));
            list = bundle.getStringArray(HEADWEAR + ".Hot");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(HEADWEAR + ".Warm") != null){
            tempList.add(Html.fromHtml("<b>Warm</b>"));
            list = bundle.getStringArray(HEADWEAR + ".Warm");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(HEADWEAR + ".Chilly") != null){
            tempList.add(Html.fromHtml("<b>Chilly</b>"));
            list = bundle.getStringArray(HEADWEAR + ".Chilly");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.getStringArray(HEADWEAR + ".Freezing") != null) {
            tempList.add(Html.fromHtml("<b>Freezing</b>"));
            list = bundle.getStringArray(HEADWEAR + ".Freezing");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }

        // Add Upperbody items to arraylist
        tempList.add(Html.fromHtml("<b>UPPERBODY</b>"));
        bundle = getIntent().getBundleExtra(UPPERBODY + ".Bundle");
        if(bundle.get(UPPERBODY + ".Hot") != null){
            tempList.add(Html.fromHtml("<b>Hot</b>"));
            list = bundle.getStringArray(UPPERBODY + ".Hot");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(UPPERBODY + ".Warm") != null){
            tempList.add(Html.fromHtml("<b>Warm</b>"));
            list = bundle.getStringArray(UPPERBODY + ".Warm");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(UPPERBODY + ".Chilly") != null){
            tempList.add(Html.fromHtml("<b>Chilly</b>"));
            list = bundle.getStringArray(UPPERBODY + ".Chilly");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.getStringArray(UPPERBODY + ".Freezing") != null) {
            tempList.add(Html.fromHtml("<b>Freezing</b>"));
            list = bundle.getStringArray(UPPERBODY + ".Freezing");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }

        // Add Lowerbody items to arraylist
        tempList.add(Html.fromHtml("<b>LOWERBODY</b>"));
        bundle = getIntent().getBundleExtra(LOWERBODY + ".Bundle");
        if(bundle.get(LOWERBODY + ".Hot") != null){
            tempList.add(Html.fromHtml("<b>Hot</b>"));
            list = bundle.getStringArray(LOWERBODY + ".Hot");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(LOWERBODY + ".Warm") != null){
            tempList.add(Html.fromHtml("<b>Warm</b>"));
            list = bundle.getStringArray(LOWERBODY + ".Warm");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(LOWERBODY + ".Chilly") != null){
            tempList.add(Html.fromHtml("<b>Chilly</b>"));
            list = bundle.getStringArray(LOWERBODY + ".Chilly");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.getStringArray(LOWERBODY + ".Freezing") != null) {
            tempList.add(Html.fromHtml("<b>Freezing</b>"));
            list = bundle.getStringArray(LOWERBODY + ".Freezing");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }

        // Add Shoes items to arraylist
        tempList.add(Html.fromHtml("<b>SHOES</b>"));
        bundle = getIntent().getBundleExtra(SHOES + ".Bundle");
        if(bundle.get(SHOES + ".Hot") != null){
            tempList.add(Html.fromHtml("<b>Hot</b>"));
            list = bundle.getStringArray(SHOES + ".Hot");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(SHOES + ".Warm") != null){
            tempList.add(Html.fromHtml("<b>Warm</b>"));
            list = bundle.getStringArray(SHOES + ".Warm");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.get(SHOES + ".Chilly") != null){
            tempList.add(Html.fromHtml("<b>Chilly</b>"));
            list = bundle.getStringArray(SHOES + ".Chilly");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }
        if(bundle.getStringArray(SHOES + ".Freezing") != null) {
            tempList.add(Html.fromHtml("<b>Freezing</b>"));
            list = bundle.getStringArray(SHOES + ".Freezing");
            for (int i = 0; i < list.length; i++) {
                tempList.add(Html.fromHtml(list[i]));
            }
        }

        // Create the String list to be added to view
        Spanned[] result = new Spanned[tempList.size()];
        for(int i = 0; i < tempList.size(); i++){
            result[i] = tempList.get(i);
        }

        return result;
    }
}
