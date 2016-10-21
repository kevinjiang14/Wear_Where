package com.example.kevin.wear_where;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VacationDataActivity extends AppCompatActivity {

    private String city, state, leaveDate, returnDate;
    private int leaveMonth, leaveDay, leaveYear;
    private int returnMonth, returnDay, returnYear;

    private TextView cityTV, stateTV, leaveDateTV, returnDateTV;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_data);

        // Get extra variables passed from MainActivity
        city = getIntent().getExtras().getString("city");
        state = getIntent().getExtras().getString("state");
        leaveDate = getIntent().getExtras().getString("leaveDate");
        returnDate = getIntent().getExtras().getString("returnDate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        submitButton = (Button) findViewById(R.id.returnButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (city == null || state == null) {
            Toast.makeText(this, "No valid location found", Toast.LENGTH_LONG).show();
        } else if (leaveDate == null) {
            Toast.makeText(this, "No leave date found", Toast.LENGTH_LONG).show();
        } else if (returnDate == null) {
            Toast.makeText(this, "No return date found", Toast.LENGTH_LONG).show();
        }

        cityTV = (TextView) findViewById(R.id.City);
        stateTV = (TextView) findViewById(R.id.State);
        leaveDateTV = (TextView) findViewById(R.id.LeaveDate);
        returnDateTV = (TextView) findViewById(R.id.ReturnDate);

        cityTV.setText(city);
        stateTV.setText(state);

        leaveDateTV.setText(leaveDate);
        returnDateTV.setText(returnDate);

    }
}
