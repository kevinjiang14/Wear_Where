package com.example.kevin.wear_where.wear;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kevin.wear_where.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.kevin.wear_where.MainActivity.MESSAGE;

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
            list.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.itemname, clothingList));
        }
        else {
            String[] clothingList = CreateVacationSuggestionList();
            list.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.itemname, clothingList));
        }
    }

    public String[] CreateVacationSuggestionList(){
        ArrayList<String> tempList = new ArrayList<>();
        String[] list;

        // Add Headwear items to arraylist
        tempList.add("Headwear");
        list = getIntent().getStringArrayExtra("Headwear");
        for(int i = 0; i < list.length; i++){
            tempList.add(list[i]);
        }
        // Add Upperbody items to arraylist
        tempList.add("Upperbody");
        list = getIntent().getStringArrayExtra("Upperbody");
        for(int i = 0; i < list.length; i++){
            tempList.add(list[i]);
        }
        // Add Lowerbody items to arraylist
        tempList.add("Lowerbody");
        list = getIntent().getStringArrayExtra("Lowerbody");
        for(int i = 0; i < list.length; i++){
            tempList.add(list[i]);
        }
        // Add Shoes items to arraylist
        tempList.add("Shoes");
        list = getIntent().getStringArrayExtra("Shoes");
        for(int i = 0; i < list.length; i++){
            tempList.add(list[i]);
        }

        // Create the String list to be added to view
        String[] result = new String[tempList.size()];
        for(int i = 0; i < tempList.size(); i++){
            result[i] = tempList.get(i);
        }

        return result;
    }
}
