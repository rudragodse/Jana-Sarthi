package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.janasarthi.R;
import com.example.janasarthi.Users.Counsellor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewCounsellorActivity extends AppCompatActivity {

    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence;
    String name,email,qualification,specialization;
    List<CounsellorData>counsellormodels;
    RecyclerView counsellorcardrecyclerview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(ViewCounsellorActivity.this, R.color.yellowtheme));

        setContentView(R.layout.activity_view_counsellor);
        Objects.requireNonNull(getSupportActionBar()).hide();

        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Counsellors");
        counsellormodels = new ArrayList<>();

        mrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    Log.i("counsellors",ds.toString());
                    ;

                    Log.i("dsvalue", Objects.requireNonNull(ds.getValue(Counsellor.class)).getEmail() );
                    name = Objects.requireNonNull(ds.getValue(Counsellor.class)).getName();
                    email = Objects.requireNonNull(ds.getValue(Counsellor.class)).getEmail();
                    qualification = Objects.requireNonNull(ds.getValue(Counsellor.class)).getQualification();
                    specialization = Objects.requireNonNull(ds.getValue(Counsellor.class)).getSpecialization();
                    counsellormodels.add(new CounsellorData(name,qualification,specialization));



                }
                counsellorcardrecyclerview = findViewById(R.id.counsellorcardrecyclerview);
                Log.i("sizeoflist",String.valueOf(counsellormodels.size()));
                CounsellorRecyclerAdapter counsellorRecyclerAdapter = new CounsellorRecyclerAdapter(counsellormodels,ViewCounsellorActivity.this);
                LinearLayoutManager horizontallayoutmanager = new LinearLayoutManager(ViewCounsellorActivity.this,LinearLayoutManager.HORIZONTAL,false);
                counsellorcardrecyclerview.setLayoutManager(horizontallayoutmanager);
                HorizontalItemDecoration horizontalItemDecoration = new HorizontalItemDecoration(50);
                counsellorcardrecyclerview.addItemDecoration(horizontalItemDecoration);
                counsellorcardrecyclerview.setAdapter(counsellorRecyclerAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}