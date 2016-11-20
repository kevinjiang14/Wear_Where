package com.example.kevin.wear_where.wear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kevin.wear_where.R;

import org.w3c.dom.Text;

import static com.example.kevin.wear_where.MainActivity.FIRSTMESSAGE;
import static com.example.kevin.wear_where.MainActivity.SECONDMESSAGE;

import java.util.ArrayList;

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

        ListView currentList = (ListView)findViewById(R.id.currentList);
        ListView laterList = (ListView)findViewById(R.id.laterList);
        TextView currentText = (TextView)findViewById(R.id.currentClothes);
        TextView laterText = (TextView)findViewById(R.id.laterClothes);
        Intent intent = getIntent();
        if(intent.getStringArrayExtra(FIRSTMESSAGE) != null && intent.getStringArrayExtra(SECONDMESSAGE) != null) {
            String[] currentClothingList = intent.getStringArrayExtra(FIRSTMESSAGE);
            String[] laterClothingList = intent.getStringArrayExtra(SECONDMESSAGE);
            int hour = intent.getIntExtra("TIME", 25);
            String ampm = intent.getStringExtra("AMPM");
            String currentCondition = intent.getStringExtra("CONDITION");
            String laterCondition = intent.getStringExtra("LATERCONDITION");
            String changedTemp = intent.getStringExtra("TEMP");
            String currentTemp = intent.getStringExtra("THISTEMP");

            currentText.setText("Clothes for current weather: "+currentTemp+ (char) 0x00B0 + " F, " + currentCondition);
            if (hour != 25) {
                laterText.setText(hour+1 + " hour(s) later it's " + changedTemp + (char) 0x00B0 + " F, "+ laterCondition +". Consider bringing these");
            }
            currentList.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.itemname, currentClothingList));
            laterList.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.itemname, laterClothingList));
        }
        else {
            currentText.setText("What to bring");
            laterText.setText("Consider also bringing");
            Spanned[] clothingList = CreateVacationSuggestionList();
            currentList.setAdapter(new ArrayAdapter<>(this, R.layout.mylist, R.id.itemname, clothingList));
            clothingList = CreateVacationConsiderationList();
            laterList.setAdapter(new ArrayAdapter<>(this, R.layout.mylist, R.id.itemname, clothingList));
        }
        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public Spanned[] CreateVacationSuggestionList(){
        ArrayList<Spanned> tempList = new ArrayList<>();
        Bundle bundle;
        String[] list;
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
    public Spanned[] CreateVacationConsiderationList(){
        ArrayList<Spanned> tempList = new ArrayList<>();
        Bundle bundle;
        String[] list;
        // Add Upperbody items to arraylist
        tempList.add(Html.fromHtml("<b>UPPERBODY</b>"));
        bundle = getIntent().getBundleExtra(UPPERBODY + ".BundleConsider");
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
        bundle = getIntent().getBundleExtra(LOWERBODY + ".BundleConsider");
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
        bundle = getIntent().getBundleExtra(SHOES + ".BundleConsider");
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
