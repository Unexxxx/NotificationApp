package com.unex.notificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.unex.notificationapp.NotifyPage;
import com.unex.notificationapp.R;

public class MainActivity extends AppCompatActivity {

    ListView lvActivities;
    ArrayAdapter<String> activitiesArrayAdapter;
    String[] mStringArray = {"ONLINE CHAPTER MEETING\nMONDAY TO SATURDAY\n12nn","MONTHLY CHAPTER MEETING\nARAW-ARAW\n8pm", "SELF-AWARENESS SEMINAR\nFEBRUARY 27, 2021\n8AM", "HOME DECOR\nDIKO ALAM\nsecret", "WELCOME BEACH\nWELCOMING NANAY MO\ndiko alam"};
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvActivities = findViewById(R.id.lv_activities);
        activitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringArray);
        lvActivities.setAdapter(activitiesArrayAdapter);
    }

    public void onClickAddNotif(View v){
        Intent intent = new Intent(this, NotifyPage.class);
        startActivity(intent);
    }
}