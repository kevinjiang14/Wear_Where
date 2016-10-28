package com.example.kevin.wear_where.wear;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kevin.wear_where.R;

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
        String[] clothingList = intent.getStringArrayExtra(MESSAGE);
        list.setAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.itemname, clothingList));

    }
}
