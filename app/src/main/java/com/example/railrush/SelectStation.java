package com.example.railrush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SelectStation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_station);
        LinearLayout stationsLL = findViewById(R.id.stationsList);
        try{
            InputStream fileInputStream = getAssets().open("stations.json");
            ArrayList<String> stationsList = new ArrayList<>();
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            String jsonString = new String(buffer);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject station = jsonArray.getJSONObject(i);
                Log.w("JSON",station.getString("name"));
                stationsList.add(station.getString("name"));
                TextView stationTV = new TextView(this);
                stationTV.setTextColor(getResources().getColor(R.color.colorWhite));
                stationTV.setBackground(getDrawable(R.drawable.round_corners_blue));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10,10,10,10);
                layoutParams.gravity = Gravity.CENTER;
                stationTV.setLayoutParams(layoutParams);
                stationTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SelectStation.this,SelectDirection.class);
                        try{
                            intent.putExtra("station",station.getString("name"));
                        }catch (Exception e){}
                        Log.w("Station", intent.getStringExtra("station"));
                        startActivity(intent);
                    }
                });
                stationTV.setPadding(50,50,50,50);
                stationTV.setText(station.getString("name"));
                stationsLL.addView(stationTV);
            }
        }catch (Exception e){

        }
    }
}