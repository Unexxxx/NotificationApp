package com.unex.notificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unex.notificationapp.NotifyPage;
import com.unex.notificationapp.R;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class MainActivity extends AppCompatActivity {

    ListView lvActivities;
    ArrayAdapter<String> activitiesArrayAdapter;
    String[] mStringArray = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvActivities = findViewById(R.id.lv_activities);
        activitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringArray);
        lvActivities.setAdapter(activitiesArrayAdapter);

        SharedPreferences notifSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
        String notificationData = notifSharedPref.getString("notifData", null);
        Gson gson = new Gson();
        List<String> data = new ArrayList<>();
        if(notificationData != null){
            data = gson.fromJson(notificationData, new TypeToken<List<String>>(){}.getType());
        }
        mStringArray = new String[data.size()];
        mStringArray = (String[]) data.toArray(mStringArray);
        activitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringArray);
        lvActivities.setAdapter(activitiesArrayAdapter);
        lvActivities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences aklatSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
                String aklatData = aklatSharedPref.getString("notifData", null);
                Gson gson = new Gson();
                List<String> data = new ArrayList<>();
                if(aklatData != null){
                    data = gson.fromJson(aklatData, new TypeToken<List<String>>(){}.getType());
                }

                //remove data from listview
//                data.remove(mStringArray[i]);
//                activitiesArrayAdapter.notifyDataSetChanged();

                mStringArray = new String[data.size()];
                mStringArray = (String[]) data.toArray(mStringArray);
                activitiesArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mStringArray);
                lvActivities.setAdapter(activitiesArrayAdapter);
                saveToSharedPref(data);
                return false;
            }
        });

    }

    public void onClickAddNotif(View v){
        Intent intent = new Intent(this, NotifyPage.class);
        startActivity(intent);
    }

    private void saveToSharedPref(List<String> wholeData){
        Gson gson = new Gson();
        String wholeDataString = gson.toJson(wholeData);
        SharedPreferences notifSharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = notifSharedPref.edit();
        editor.putString("notifData", wholeDataString);
        editor.apply();
    }
}