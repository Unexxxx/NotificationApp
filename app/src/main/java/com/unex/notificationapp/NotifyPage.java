package com.unex.notificationapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.unex.notificationapp.R;
import com.vivekkaushik.datepicker.DatePickerTimeline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotifyPage extends AppCompatActivity {

    EditText etLabel;
    CollapsibleCalendar viewCalendar;
    Day day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
//        View view = getSupportActionBar().getCustomView();


        final CollapsibleCalendar collapsibleCalendar = findViewById(R.id.calendarView);

        etLabel = findViewById(R.id.et_label);


        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                day = viewCalendar.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: " + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
//                Toast.makeText(NotifyPage.this,"Selected Day: " + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }

            @Override
            public void onDayChanged() {

            }

            @Override
            public void onClickListener() {

            }
        });
    }



    public void onClickBack(View v){
        finish();
    }
    public void onClickSave(View v){

        String date = String.valueOf(day.getMonth() + day.getDay() + day.getYear());
        String label = etLabel.getText().toString();
        saveToSharedPref(label + " " + date);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    private void saveToSharedPref(String activityData){
        Gson gson = new Gson();

        SharedPreferences aklatSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
        String aklatData = aklatSharedPref.getString("notifData", null);

        ArrayList<String> data = new ArrayList<>();
        if(aklatData != null){
            data = gson.fromJson(aklatData, new TypeToken<List<String>>(){}.getType());
        }
        data.add(activityData);

        String dataString = gson.toJson(data);
        SharedPreferences.Editor editor = aklatSharedPref.edit();
        editor.putString("notifData", dataString);
        editor.commit();
    }

}