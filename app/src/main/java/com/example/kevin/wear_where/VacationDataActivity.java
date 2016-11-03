package com.example.kevin.wear_where;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kevin.wear_where.AsyncTask.PlannerAST;
import com.example.kevin.wear_where.WundergroundData.Planner.PlannerObject;

public class VacationDataActivity extends AppCompatActivity {

    // Variables containing information passed from MainActivity
    private String city, state, leaveTime, returnTime;

    // Linear Layout we are adding timeframe layout to
    private LinearLayout tfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_data);


        // Assign views to variables
        TextView destinationTV = (TextView) findViewById(R.id.Destination);
        TextView dateTV = (TextView) findViewById(R.id.Date);

        // Button to return back to MainActivity
        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Button to save vacation
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Set what clicking this button does
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
        // Assign the Linear Layout to variable
        tfList = (LinearLayout) findViewById(R.id.TimeFrameList);
        // Get the length of the vacation
        int vacationDuration = CalculateDuration(leaveTime, returnTime);
        // Get the suitable timeframe length
        int vacationTimeFrameLength = CalculateTimeFrame(vacationDuration);
        // Get the number of timeframes given the duration and timeframe length
        int numofTimeFrames = CalculateNumberofTimeFrames(vacationDuration, vacationTimeFrameLength);
        // Get an array of the timeframes being looked up
        final String timeFrames[] = CreateTimeFrames(vacationTimeFrameLength, leaveTime, returnTime, numofTimeFrames);

        for(int i = 1; i < timeFrames.length; i++){
            // Layout Inflater
            LayoutInflater li = LayoutInflater.from(this);
            // Timeframe view to be added to linear layout list
            final View timeframe = li.inflate(R.layout.timeframe, null);
            // Update text for date
            TextView tempTextView = (TextView) timeframe.findViewById(R.id.Date);
            DisplayDate(timeFrames[i - 1], timeFrames[i], tempTextView);

            new PlannerAST(timeFrames[i - 1], timeFrames[i], city, state){
                @Override
                protected void onPostExecute(PlannerObject result){
                    // Updates condition chances
                    DisplayResults(result, timeframe);
                    // Add timeframe view to
                    tfList.addView(timeframe);
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
    public void DisplayResults(PlannerObject plannerObject, View timeframe) {
        TextView tempText = (TextView) timeframe.findViewById(R.id.BelowFreezingPercent);
        tempText.setText("" + plannerObject.getBelowFreezingChance() + "%");
        tempText = (TextView) timeframe.findViewById(R.id.CloudyPercent);
        tempText.setText("" + plannerObject.getCloudyChance() + "%");
        tempText = (TextView) timeframe.findViewById(R.id.FogPercent);
        tempText.setText("" + plannerObject.getFogChance() + "%");
        tempText = (TextView) timeframe.findViewById(R.id.SnowPercent);
        tempText.setText("" + plannerObject.getSnowChance() + "%");
        tempText = (TextView) timeframe.findViewById(R.id.RainPercent);
        tempText.setText("" + plannerObject.getRainChance() + "%");
        tempText = (TextView) timeframe.findViewById(R.id.WindyPercent);
        tempText.setText("" + plannerObject.getWindyChance() + "%");
        tempText = (TextView) timeframe.findViewById(R.id.HumidPercent);
        tempText.setText("" + plannerObject.getHumidChance() + "%");
    }

    public void DisplayDate(String start, String end, TextView view){
        view.setText("" + start.charAt(0) + start.charAt(1) + "/" + start.charAt(2) + start.charAt(3) + " - " + end.charAt(0) + end.charAt(1) + "/" + end.charAt(2) + end.charAt(3));
    }
}
