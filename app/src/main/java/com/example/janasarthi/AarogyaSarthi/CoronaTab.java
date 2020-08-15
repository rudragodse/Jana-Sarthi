package com.example.janasarthi.AarogyaSarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.janasarthi.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class CoronaTab extends AppCompatActivity {

    MaterialButton taketestagainbtn;
    MaterialButton reminderbtn;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_tab);
        Objects.requireNonNull(getSupportActionBar()).hide();

        taketestagainbtn = findViewById(R.id.taketestagainbtn);
        taketestagainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoronaTab.this, TakeTest.class);
                startActivity(intent);
            }
        });
    }
}