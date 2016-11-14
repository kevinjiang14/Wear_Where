package com.example.kevin.wear_where.wear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kevin.wear_where.R;
import static com.example.kevin.wear_where.MainActivity.FIRSTMESSAGE;
import static com.example.kevin.wear_where.MainActivity.SECONDMESSAGE;

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

        String[] currentClothingList = intent.getStringArrayExtra(FIRSTMESSAGE);
        String[] laterClothingList = intent.getStringArrayExtra(SECONDMESSAGE);
        int hour = intent.getIntExtra("TIME", 25);
        String ampm = intent.getStringExtra("AMPM");

        currentList.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.itemname, currentClothingList));
        laterList.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.itemname, laterClothingList));
    }
}
