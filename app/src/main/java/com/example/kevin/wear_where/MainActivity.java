package com.example.kevin.wear_where;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec homeTab = tabHost.newTabSpec("homeTab");
        homeTab.setIndicator("Home");
        homeTab.setContent(R.id.layout1);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab2");
        homeTab.setIndicator("Home 2");
        homeTab.setContent(R.id.layout2);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab3");
        homeTab.setIndicator("Home 3");
        homeTab.setContent(R.id.layout3);
        tabHost.addTab(homeTab);

        homeTab = tabHost.newTabSpec("homeTab2");
        homeTab.setIndicator("Home 4");
        homeTab.setContent(R.id.layout4);
        tabHost.addTab(homeTab);
    }
}