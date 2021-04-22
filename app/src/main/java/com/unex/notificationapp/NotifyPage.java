package com.unex.notificationapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shawnlin.numberpicker.NumberPicker;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.androidexception.andexalertdialog.AndExAlertDialog;
import ir.androidexception.andexalertdialog.AndExAlertDialogListener;
import ir.androidexception.andexalertdialog.Font;
import ir.androidexception.andexalertdialog.InputType;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class NotifyPage extends AppCompatActivity {

    CollapsibleCalendar viewCalendar;
    Day day;
    NumberPicker npHour, npMin;
    ListView lvLblDes;
    ArrayAdapter<String> lblDesAdapter;
    TextView tvTitle, tvDes, tvVenue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
//        View view = getSupportActionBar().getCustomView();


//        lvLblDes = findViewById(R.id.lv_lbl_des);
        tvTitle = findViewById(R.id.tv_title);
        tvDes = findViewById(R.id.tv_des);
        tvVenue = findViewById(R.id.tv_venue);
        viewCalendar = findViewById(R.id.calendarView);
        npHour = findViewById(R.id.np_hour);
        npMin = findViewById(R.id.np_min);

        ScrollView scrollView = (ScrollView) findViewById(R.id.sv_bouncyE);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);


//        lblDesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStringArray);
//        lvLblDes.setAdapter(lblDesAdapter);

        Calendar currentTime = Calendar.getInstance();
        int currentHourIn24Format = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentTime.get(Calendar.MINUTE);

        npHour.setValue(currentHourIn24Format);
        npMin.setValue(currentMinute);


        viewCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
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

    public void onClickTitle(View v){
        alertTitleDialog();
    }

    public void onClickDes(View v){
        alertDesDialog();
    }
    public void onClickVenue(View v){
        alertVenueDialog();
    }

    public void alertTitleDialog(){
        new AndExAlertDialog.Builder(this)
                .setTitle("Set Title")
                .setMessage("")
                .setPositiveBtnText("OK")
                .setNegativeBtnText("Cancel")
                .setCancelableOnTouchOutside(true)
                .setFont(Font.QUICK_SAND)
                .setImage(R.drawable.tick, 0)
                .setEditText(true, false, "Title", InputType.TEXT_MULTI_LINE)
                .OnPositiveClicked(new AndExAlertDialogListener() {
                    @Override
                    public void OnClick(String input) {
                        tvTitle.setText(input);
                    }
                })
                .OnNegativeClicked(new AndExAlertDialogListener() {
                    @Override
                    public void OnClick(String input) {
                        finish();
                    }
                })
                .setTitleTextColor(getTitleColor())
                .setMessageTextColor(getTitleColor())
                .setButtonTextColor(getTitleColor())
                .build();
    }
    public void alertDesDialog(){
        new AndExAlertDialog.Builder(this)
                .setTitle("Set Description")
                .setMessage("")
                .setPositiveBtnText("OK")
                .setNegativeBtnText("Cancel")
                .setCancelableOnTouchOutside(true)
                .setFont(Font.QUICK_SAND)
                .setImage(R.drawable.tick, 0)
                .setEditText(true, false, "Description", InputType.TEXT_MULTI_LINE)
                .OnPositiveClicked(new AndExAlertDialogListener() {
                    @Override
                    public void OnClick(String input) {
                        tvDes.setText(input);
                    }
                })
                .OnNegativeClicked(new AndExAlertDialogListener() {
                    @Override
                    public void OnClick(String input) {
                        finish();
                    }
                })
                .setTitleTextColor(getTitleColor())
                .setMessageTextColor(getTitleColor())
                .setButtonTextColor(getTitleColor())
                .build();
    }
    public void alertVenueDialog(){
        new AndExAlertDialog.Builder(this)
                .setTitle("Set Venue")
                .setMessage("")
                .setPositiveBtnText("OK")
                .setNegativeBtnText("Cancel")
                .setCancelableOnTouchOutside(true)
                .setFont(Font.QUICK_SAND)
                .setImage(R.drawable.tick, 0)
                .setEditText(true, false, "Description", InputType.TEXT_MULTI_LINE)
                .OnPositiveClicked(new AndExAlertDialogListener() {
                    @Override
                    public void OnClick(String input) {
                        tvVenue.setText(input);
                    }
                })
                .OnNegativeClicked(new AndExAlertDialogListener() {
                    @Override
                    public void OnClick(String input) {
                        finish();
                    }
                })
                .setTitleTextColor(getTitleColor())
                .setMessageTextColor(getTitleColor())
                .setButtonTextColor(getTitleColor())
                .build();
    }

    public void onClickBack(View v){
        finish();
    }
    public void onClickSave(View v){

        int timeH = npHour.getValue();
        int timeM = npMin.getValue();
        String taytel = tvTitle.getText().toString();
        String paliwanag = tvDes.getText().toString();
        String venue = tvVenue.getText().toString();
        String titleDes = taytel + "\n" + paliwanag + "\n" + venue;
        String time = timeH + ":" + timeM;
        saveToSharedPref(titleDes +"\n"+ (day.getMonth() + 1) + "/" + day.getDay() + "/" + day.getYear() + "\n" + time);
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