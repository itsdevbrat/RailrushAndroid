package com.example.railrush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.railrush.Adapters.TrainsRVAdapter;
import com.example.railrush.Models.Count;
import com.example.railrush.Models.Train;
import com.example.railrush.Services.CrowdInterface;
import com.example.railrush.Services.RailrushClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayTrains extends AppCompatActivity {

    private JSONArray currentStationTrains;
    private ArrayList<Train> trainsList;
    private TrainsRVAdapter trainsRVAdapter;
    private RecyclerView trainsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trains);
        trainsList = new ArrayList<>();
        String current = getIntent().getStringExtra("station");
        String dest = getIntent().getStringExtra("dest");
        trainsRV = findViewById(R.id.trainsRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayTrains.this,RecyclerView.VERTICAL,false);
        trainsRV.setLayoutManager(linearLayoutManager);

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
                if(station.getString("name").equals(current)){
                    currentStationTrains = station.getJSONArray("trainsAndTime");
                }
            }
            CrowdInterface crowdService = RailrushClient.getClient().create(CrowdInterface.class);
            for (int i = 0; i < currentStationTrains.length(); i++) {
                final JSONObject train = currentStationTrains.getJSONObject(i);
                if(train.getString("dest").equals(dest)){
                    Call<Count> countCall = crowdService.getCrowdCount(train.getString("trainId"));
                    countCall.enqueue(new Callback<Count>() {
                        @Override
                        public void onResponse(Call<Count> call, Response<Count> response) {
                            try{
                                Log.w("Count",String.valueOf(response.body().getCrowdCount()));
                                trainsList.add(new Train(train.getString("start"),train.getString("dest"),train.getString("time"),String.valueOf(response.body().getCrowdCount())));
                                trainsRVAdapter = new TrainsRVAdapter(trainsList);
                                trainsRV.setAdapter(trainsRVAdapter);
                            }catch (Exception e){
                                Log.w("CountCatch","Error on response"+e.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<Count> call, Throwable t) {
                            Log.w("SERVER ERRROR",t.toString());
                        }
                    });
                }
            }



        }catch (Exception e){
            Log.w("Count","Error Disla"+e.toString());

        }
    }
}

