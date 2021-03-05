package com.unex.notificationapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.unex.notificationapp.R;
import com.vivekkaushik.datepicker.DatePickerTimeline;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NotifyPage extends AppCompatActivity {

    private TimePicker tpAlarmTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
//        View view = getSupportActionBar().getCustomView();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DATE);

        DatePickerTimeline datePickerTimeline = findViewById(R.id.dateTimeline);
        datePickerTimeline.setInitialDate(currentYear, currentMonth, currentDay);
    }

    public void onClickBack(View v){
        finish();
    }
    public void onClickSave(View v){
        Toast.makeText(this, "Saved!!!", Toast.LENGTH_SHORT).show();
    }

}