package com.example.railrush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectDirection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_direction);

    }

    public void selectedVirar(View view) {
        Intent intent = new Intent(this,DisplayTrains.class);
        intent.putExtra("station",getIntent().getStringExtra("station"));
        intent.putExtra("dest","virar");
        startActivity(intent);
    }

    public void selectedChurchgate(View view) {
        Intent intent = new Intent(this,DisplayTrains.class);
        intent.putExtra("station",getIntent().getStringExtra("station"));
        intent.putExtra("dest","churchgate");
        startActivity(intent);
    }
}
