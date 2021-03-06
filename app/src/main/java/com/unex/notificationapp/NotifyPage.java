package com.unex.notificationapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unex.notificationapp.R;
import com.vivekkaushik.datepicker.DatePickerTimeline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotifyPage extends AppCompatActivity {

    int currentYear, currentMonth, currentDay;
    private TimePicker tpAlarmTimePicker;
    DatePickerTimeline datePickerTimeline;
    EditText etLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
//        View view = getSupportActionBar().getCustomView();

        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentDay = Calendar.getInstance().get(Calendar.DATE);

        tpAlarmTimePicker = findViewById(R.id.tp_alarm_time_Picker);
        datePickerTimeline = findViewById(R.id.date_picker);
        etLabel = findViewById(R.id.et_label);
        datePickerTimeline.setInitialDate(currentYear, currentMonth, currentDay);

    }

    public void onClickBack(View v){
        finish();
    }
    public void onClickSave(View v){

        String date = String.valueOf(currentYear + currentMonth + currentDay);
        String time = tpAlarmTimePicker.toString();
        String label = etLabel.getText().toString();
        saveToSharedPref(label + " " + date + " " + time);
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