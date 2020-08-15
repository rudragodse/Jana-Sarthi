package com.example.janasarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.janasarthi.SignUpActivities.CounsellorSignUpActivity;
import com.example.janasarthi.SignUpActivities.DoctorSignUpActivity;
import com.example.janasarthi.SignUpActivities.MentorSignUpActivity;
import com.example.janasarthi.SignUpActivities.OrganizationSignUpActivity;
import com.example.janasarthi.SignUpActivities.UserSignUpActivity;

import java.util.Objects;

public class UserRoleActivity extends AppCompatActivity {

    ImageView usersignupimgview,doctorsignupimgview,teacherimgview,Counsellorimageview,Organizationimgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_role);
        Objects.requireNonNull(getSupportActionBar()).hide();

        usersignupimgview = findViewById(R.id.usersignupimgview);
        usersignupimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRoleActivity.this, UserSignUpActivity.class);
                intent.putExtra("role","User");
                startActivity(intent);
            }
        });

        doctorsignupimgview = findViewById(R.id.doctorsignupimgview);
        doctorsignupimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRoleActivity.this, DoctorSignUpActivity.class);
                startActivity(intent);
            }
        });

        teacherimgview = findViewById(R.id.teacherimgview);
        teacherimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRoleActivity.this, MentorSignUpActivity.class);
                startActivity(intent);
            }
        });

        Counsellorimageview = findViewById(R.id.Counsellorimageview);
        Counsellorimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRoleActivity.this, CounsellorSignUpActivity.class);
                startActivity(intent);
            }
        });

        Organizationimgview = findViewById(R.id.Organizationimageview);
        Organizationimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRoleActivity.this, OrganizationSignUpActivity.class);
                startActivity(intent);
            }
        });





    }
}