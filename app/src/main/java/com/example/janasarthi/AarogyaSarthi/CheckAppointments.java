package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.janasarthi.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
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

public class CheckAppointments extends AppCompatActivity {

    DatabaseReference appointmentref,dateref,userref;
    FirebaseDatabase mdatabase;
    List<String>datesofappointment;
    ChipGroup dateschipgroup;
    FirebaseAuth mauth;
    List<String>timings;
    JSONArray jsonArray;
    List<AppointmentInfo>appointmentInfoList;
    RecyclerView appointmentRecyclerview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CheckAppointments.this,R.color.yellowtheme));
        setContentView(R.layout.activity_check_appointments);
        Objects.requireNonNull(getSupportActionBar()).hide();
        appointmentInfoList = new ArrayList<>();
        jsonArray = new JSONArray();
        appointmentRecyclerview = findViewById(R.id.appointment_recyclerview);

        mauth = FirebaseAuth.getInstance();
        final String curruser = Objects.requireNonNull(mauth.getCurrentUser()).getDisplayName();
        assert curruser != null;
        Log.i("curruser",curruser);

        mdatabase = FirebaseDatabase.getInstance();
        appointmentref = mdatabase.getReference("Counsellor Appointments");
        dateschipgroup = findViewById(R.id.check_appointments_chipgroup);
        appointmentref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(curruser))
                {
                    for(DataSnapshot snapshot1: snapshot.child(curruser).getChildren())
                    {
                        Chip chip = new Chip(dateschipgroup.getContext());
                        chip.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                        chip.setCheckable(true);
                        chip.setText(snapshot1.getKey());
                        chip.setElevation(10f);
                        dateschipgroup.addView(chip);

                        for(DataSnapshot dataSnapshot: snapshot.child(curruser).child(Objects.requireNonNull(snapshot1.getKey())).getChildren())
                        {
                            Log.i("timingslot", Objects.requireNonNull(dataSnapshot.getKey()));
                            for(DataSnapshot dataSnapshot1: snapshot.child(curruser).child(snapshot1.getKey()).child(dataSnapshot.getKey()).getChildren())
                            {
                                Log.i("userinfo", Objects.requireNonNull(dataSnapshot1.getKey()));
                                appointmentInfoList.add(new AppointmentInfo(dataSnapshot.getKey(),dataSnapshot1.getKey()));
                            }
                        }
                    }
                    Log.i("appointmentinfolist",String.valueOf(appointmentInfoList.size()));
                    //data fetched successfully;
                    AppointmentAdapter appointmentAdapter = new AppointmentAdapter(CheckAppointments.this,appointmentInfoList);
                    appointmentRecyclerview.setLayoutManager(new LinearLayoutManager(CheckAppointments.this,LinearLayoutManager.VERTICAL,false));
                    appointmentRecyclerview.setAdapter(appointmentAdapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dateschipgroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                int id = group.getCheckedChipId();
                Chip chip = findViewById(id);
                String chiptext = chip.getText().toString();
                Object ob = new Object();
                appointmentInfoList.clear();
                final DatabaseReference databaseReference = mdatabase.getReference("Counsellor Appointments"+"/"+curruser+"/"+chiptext);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                            {
                                Log.i("usertxtfromchip", Objects.requireNonNull(dataSnapshot1.getKey()));
                                AppointmentData appointmentData = dataSnapshot1.getValue(AppointmentData.class);
                                Log.i("appointmentdata",appointmentData.toString());
                                appointmentInfoList.add(new AppointmentInfo(appointmentData.getTimeslot(),appointmentData.getCurrentuser()));
                            }
                        }
                        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(CheckAppointments.this,appointmentInfoList);
                        appointmentRecyclerview.setAdapter(appointmentAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}