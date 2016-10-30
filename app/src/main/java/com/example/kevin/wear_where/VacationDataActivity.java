package com.example.kevin.wear_where;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kevin.wear_where.AsyncTask.PlannerAST;
import com.example.kevin.wear_where.WundergroundData.Planner.PlannerObject;
import com.google.android.gms.vision.text.Text;

public class VacationDataActivity extends AppCompatActivity {

    // Variables containing information passed from MainActivity
    private String city, state, leaveTime, returnTime;

    // Vacation information views
    private TextView destinationTV, dateTV;
    private Button returnButton;

    // Vacation Data views
    private TextView frameDate, belowFreezing1, cloudy1, fog1, snow1, rain1, windy1, humid1;

    private int vacationDuration, vacationTimeFrameLength, numofTimeFrames;

    private PlannerObject plannerObject;

    private String timeFrames[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_data);


        // Assign views to variables
        destinationTV = (TextView) findViewById(R.id.Destination);
        dateTV = (TextView) findViewById(R.id.Date);

        // Button to return back to MainActivity
        returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get extra variables passed from MainActivity
        city = getIntent().getExtras().getString("city");
        state = getIntent().getExtras().getString("state");
        leaveTime = getIntent().getExtras().getString("leaveDate");
        returnTime = getIntent().getExtras().getString("returnDate");

        // Assign text to views
        destinationTV.setText(city + ", " + state);
        dateTV.setText(leaveTime + " - " + returnTime);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Get the length of the vacation
        vacationDuration = CalculateDuration(leaveTime, returnTime);
        // Get the suitable timeframe length
        vacationTimeFrameLength = CalculateTimeFrame(vacationDuration);
        // Get the number of timeframes given the duration and timeframe length
        numofTimeFrames = CalculateNumberofTimeFrames(vacationDuration, vacationTimeFrameLength);
        // Get an array of the timeframes being looked up
        timeFrames = CreateTimeFrames(vacationTimeFrameLength, leaveTime, returnTime, numofTimeFrames);

        AssignViews();


        for(int i = 1; i < timeFrames.length; i++){
            new PlannerAST(timeFrames[0], timeFrames[timeFrames.length - 1], city, state){
                @Override
                protected void onPostExecute(PlannerObject result){
                    plannerObject = result;
                    DisplayDate(timeFrames[0], timeFrames[timeFrames.length - 1]);
                    DisplayResults();
                }
            }.execute();
        }

        // TODO: Do validation in MainActivity that'll determine if this activity getes loaded
//        if (city == null || state == null) {
//            Toast.makeText(this, "No valid location found", Toast.LENGTH_LONG).show();
//        } else if (leaveTime == null) {
//            Toast.makeText(this, "No leave date found", Toast.LENGTH_LONG).show();
//        } else if (returnTime == null) {
//            Toast.makeText(this, "No return date found", Toast.LENGTH_LONG).show();
//        }
    }

    // Calculates the duration of stay roughly estimating all months to be 30days
    public int CalculateDuration(String leaving, String returning) {
        int tempLeaveDay = GetDay(leaving);
        int tempReturnDay = GetDay(returning);
        int tempLeaveMonth = GetMonth(leaving);
        int tempReturnMonth = GetMonth(returning);

        int tempDuration = 0;
        // Calculate all days of the vacation months
        if (tempReturnMonth > tempLeaveMonth) {
            // Duration of at least a month
            while (tempLeaveMonth < tempReturnMonth) {
                tempDuration += DaysInMonth(tempLeaveMonth);
                tempLeaveMonth++;
            }
        }
        // Subtract the days before that you have not left yet from the first month and add the remaining days of the return month
        tempDuration = tempDuration - tempLeaveDay + tempReturnDay;

        return tempDuration;
    }

    // Returns the day from a given string
    public int GetDay(String date) {
        String temp = "" + date.charAt(3) + date.charAt(4);
        return Integer.parseInt(temp);
    }

    // Returns the month of a given string
    public int GetMonth(String date) {
        String temp = "" + date.charAt(0) + date.charAt(1);
        return Integer.parseInt(temp);
    }

    // Calculates an appropriate timeframe for a given duration
    public int CalculateTimeFrame(int duration) {
        if (duration >= 36 && duration <= 150) {
            // Return 30days timeframe if duration is from 1-5months
            return 30;
        } else if (duration >= 8 && duration <= 35) {
            // Return 7days timeframe if duration is from 1-5weeks
            return 7;
        } else return 1;
    }

    // Returns the number of days in a month
    public int DaysInMonth(int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 2) {
            return 28;
        } else return 30;
    }

    // Creates the beginning timeframes given the length of timeframe and vacation dates
    public String[] CreateTimeFrames(int timeFrameLength, String start, String end, int frames) {
        int tempStartMonth = GetMonth(start);
        int tempStartDay = GetDay(start);
        int tempEndMonth = GetMonth(end);
        int tempEndDay = GetDay(end);
        String tempTimeFrames[] = new String[frames + 1];

        // Add first beginning timeframe to array
        tempTimeFrames[0] = FormatDate(tempStartMonth, tempStartDay);

        // Initialize index for after first timeframe
        int index = 1;

        // Add every other beginning timeframe to array
        while (tempStartMonth <= tempEndMonth && tempStartDay != tempEndDay) {
            // Increment date to next timeframe
            tempStartDay += timeFrameLength;

            if(tempStartDay > DaysInMonth(tempStartMonth)){
                // If day exceeds number of days in month then move to the next month
                tempStartDay -= DaysInMonth(tempStartMonth);
                tempStartMonth++;
            }

            if(tempStartMonth == tempEndMonth && tempStartDay > tempEndDay){
                tempStartDay = tempEndDay;
            }

            // Add timeframe to index and increment index
            tempTimeFrames[index] = FormatDate(tempStartMonth, tempStartDay);
            index++;
        }

        return tempTimeFrames;
    }

    // Calculates the number of timeframes given the duration and timeframe length of the vacation
    public int CalculateNumberofTimeFrames(int duration, int frameLength){
        int temp;

        if(duration % frameLength != 0){
            temp = duration / frameLength + 1;
        }
        else temp = duration / frameLength;

        return temp;
    }

    public String FormatDate(int month, int day) {
        String tempDate;
        if (month < 10) {
            tempDate = "0" + Integer.toString(month);
        } else tempDate = Integer.toString(month);

        if (day < 10) {
            tempDate = tempDate + "0" + Integer.toString(day);
        } else tempDate = tempDate + Integer.toString(day);
        return tempDate;
    }

    // Displays the data of the given timeframe
    public void DisplayResults() {
        belowFreezing1.setText("" + plannerObject.getBelowFreezingChance() + "%");
        cloudy1.setText("" + plannerObject.getCloudyChance() + "%");
        fog1.setText("" + plannerObject.getFogChance() + "%");
        snow1.setText("" + plannerObject.getSnowChance() + "%");
        rain1.setText("" + plannerObject.getRainChance() + "%");
        windy1.setText("" + plannerObject.getWindyChance() + "%");
        humid1.setText("" + plannerObject.getHumidChance() + "%");
    }

    public void DisplayDate(String start, String end){
        frameDate.setText("" + start.charAt(0) + start.charAt(1) + "/" + start.charAt(2) + start.charAt(3) + " - " + end.charAt(0) + end.charAt(1) + "/" + end.charAt(2) + end.charAt(3));
    }

    public void AssignViews(){
        frameDate = (TextView) findViewById(R.id.Date1);
        belowFreezing1 = (TextView) findViewById(R.id.BelowFreezingPercent1);
        cloudy1 = (TextView) findViewById(R.id.CloudyPercent1);
        fog1 = (TextView) findViewById(R.id.FogPercent1);
        snow1 = (TextView) findViewById(R.id.SnowPercent1);
        rain1 = (TextView) findViewById(R.id.RainPercent1);
        windy1 = (TextView) findViewById(R.id.WindyPercent1);
        humid1 = (TextView) findViewById(R.id.HumidPercent1);
    }
}
