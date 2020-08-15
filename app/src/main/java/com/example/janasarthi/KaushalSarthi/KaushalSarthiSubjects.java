package com.example.janasarthi.KaushalSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.janasarthi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ServiceConfigurationError;

public class KaushalSarthiSubjects extends AppCompatActivity {

    FirebaseDatabase mdatabase;
    FirebaseAuth mauth;
    DatabaseReference mrefrence;
    JSONArray selectedcoursearray;
    TextView sectornamefromintent;
    ImageView back_btn_imageview;
    RecyclerView sector_courses_recyclerview;
    List<CourseInfo> courseInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaushal_sarthi_subjects);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = getIntent();
        final String clickedtab = intent.getStringExtra("nameofsector");
        assert clickedtab != null;
        Log.i("clickedtab",clickedtab);
        courseInfoList = new ArrayList<>();

        //textview set
        sectornamefromintent = findViewById(R.id.sectornamefromintent);
        sectornamefromintent.setText(clickedtab);

        //back btn set
        back_btn_imageview = findViewById(R.id.back_btn_imageview);
        back_btn_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(KaushalSarthiSubjects.this,KaushalSarthiHomePage.class);
                startActivity(intent1);
            }
        });

        //recyclerview set;
        sector_courses_recyclerview = findViewById(R.id.sector_courses_recyclerview);


        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Kaushal Sarthi/SectorsData");
        selectedcoursearray = new JSONArray();

        mrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Log.i("returned val", Objects.requireNonNull(dataSnapshot.getValue()).toString());
                    Object ob = dataSnapshot.getValue();
                    String json = new Gson().toJson(ob);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String coursename = jsonObject.getString("Sector Name");
                        if(coursename.equals(clickedtab))
                        {
                            selectedcoursearray.put(jsonObject);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("selectedcourse",selectedcoursearray.toString());
                for(int i=0;i<selectedcoursearray.length();i++)
                {
                    try {
                        JSONObject jo = selectedcoursearray.getJSONObject(i);
                        Log.i("jo",jo.toString());
                        String coursename = jo.getString("NAME OF THE TRADE");
                        String category = jo.getString("Trade Type");
                        String duration = jo.getString("Duration");
                        courseInfoList.add(new CourseInfo(clickedtab,coursename,category,duration));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("coursesinfolist",courseInfoList.toString());
                Log.i("selectedcourselen",String.valueOf(selectedcoursearray.length()));
                CoursesRecyclerAdapter coursesRecyclerAdapter = new CoursesRecyclerAdapter(courseInfoList,KaushalSarthiSubjects.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KaushalSarthiSubjects.this,LinearLayoutManager.VERTICAL,false);
                sector_courses_recyclerview.setLayoutManager(linearLayoutManager);
                CoursesItemDecoration coursesItemDecoration = new CoursesItemDecoration(30);
                sector_courses_recyclerview.addItemDecoration(coursesItemDecoration);
                sector_courses_recyclerview.setAdapter(coursesRecyclerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}