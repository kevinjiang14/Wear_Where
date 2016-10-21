package com.example.kevin.wear_where;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;

public class VacationDataActivity extends AppCompatActivity {

    private String city, state;
    private int leaveMonth, leaveDay, leaveYear;
    private int returnMonth, returnDay, returnYear;

    private TextView cityTV, stateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_data);

        // Get extra variables passed from MainActivity
        city = getIntent().getExtras().getString("city");
        state = getIntent().getExtras().getString("state");

        leaveMonth = getIntent().getExtras().getInt("leaveMonth");
        leaveDay = getIntent().getExtras().getInt("leaveDay");
        leaveYear = getIntent().getExtras().getInt("leaveYear");

        returnMonth = getIntent().getExtras().getInt("returnMonth");
        returnDay = getIntent().getExtras().getInt("returnDay");
        returnYear = getIntent().getExtras().getInt("returnYear");
    }

    @Override
    protected void onStart(){
        super.onStart();

        cityTV = (TextView) findViewById(R.id.City);
        stateTV = (TextView) findViewById(R.id.State);

        cityTV.setText(city);
        stateTV.setText(state);
    }
}
