package com.example.janasarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageView app_logo_img;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        app_logo_img = findViewById(R.id.app_logo_img);
        mauth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser  user = mauth.getCurrentUser();
        if(user == null)
        {
            int SPLASH_DISPLAY_LENGTH = 3000;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,SignInActivity.class);

                    startActivity(intent);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
        else
        {
            int SPLASH_DISPLAY_LENGTH = 3000;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,JanaSarthiHomePageActivity.class);

                    startActivity(intent);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }

    }
}