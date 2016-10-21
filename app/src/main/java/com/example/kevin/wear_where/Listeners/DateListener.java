package com.example.kevin.wear_where.Listeners;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Kevin Jiang on 10/19/16.
 */

public class DateListener implements DatePickerDialog.OnDateSetListener {

    private EditText ETview;
    private Calendar calendar;

    public DateListener(EditText editText, Calendar calendar){
        ETview = editText;
        this.calendar = calendar;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        UpdateText(ETview);
    }

    public void UpdateText(EditText textview){
        textview.setText("" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar
                .get(Calendar.YEAR));
    }

    // Gets the formatted date being passed to Weather Underground API for past year's data on those date
    public String getDateFormatted(){
        String formattedMonth;
        String formattedDay;

        // Pre-pend 0 to month if month is only 1 digit
        if(getMonth() < 10){
            formattedMonth = "0" + getMonth();
        } else formattedMonth = "" + getMonth();

        // Pre-pend 0 to day if day is only 1 digit
        if(getDay() < 10){
            formattedDay = "0" + getDay();
        } else formattedDay = "" + getDay();

        String formatted = formattedMonth + formattedDay;
        return formatted;
    }

    public String getFormattedDay(){
        String formattedDay;
        if(getDay() < 10){
            formattedDay = "0" + getDay();
        } else formattedDay = "" + getDay();

        return formattedDay;
    }

    public String getFormattedMonth(){
        String formattedMonth;
        if(getMonth() < 10){
            formattedMonth = "0" + getMonth();
        } else formattedMonth = "" + getMonth();

        return formattedMonth;
    }

    public int getDay(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonth(){
        return calendar.get(Calendar.MONTH);
    }

    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

}
