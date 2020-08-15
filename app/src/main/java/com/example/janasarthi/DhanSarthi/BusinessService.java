package com.example.janasarthi.DhanSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.janasarthi.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
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

public class BusinessService extends AppCompatActivity {

    DatabaseReference businessref;
    FirebaseDatabase mdatabase;
    JSONArray queriedobjectsarray;
    List<SubServicesInfo> subserviceslist;
    RecyclerView subservicerecyclerview;
    ChipGroup nameofservicechipgroup;
    List<SubServicesInfo>chipselectedlist;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BusinessService.this,DhanSarthiHomePage.class);
        intent.putExtra("updatespinner","true");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_service);
        Objects.requireNonNull(getSupportActionBar()).hide();
        final Intent intent = getIntent();
        final BusinessInfo intentobj = (BusinessInfo) intent.getSerializableExtra("BusinessInfo");
        assert intentobj != null;
        final String business_state = intentobj.getBusiness_state();
        final String business_sector = intentobj.getBusiness_sector();
        final String business_stage = intentobj.getBusiness_stage();
        mdatabase = FirebaseDatabase.getInstance();
        businessref = mdatabase.getReference("Dhan sarthi");
        subservicerecyclerview = findViewById(R.id.business_services_recyclerview);
        nameofservicechipgroup = findViewById(R.id.nameofservicechipgroup);








        queriedobjectsarray = new JSONArray();
        subserviceslist = new ArrayList<>();
        chipselectedlist = new ArrayList<>();
        businessref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.i("ob", Objects.requireNonNull(snapshot.getValue()).toString());
                Object ob = snapshot.getValue();
                String json = new Gson().toJson(ob);
                try {
                    JSONObject jo = new JSONObject(json);
                    JSONArray ja = jo.getJSONArray("business_services");
                    Log.i("ja",ja.toString());
                    //data fetched successfully;
                    for(int i=0;i<ja.length();i++)
                    {
                        JSONObject jobj = ja.getJSONObject(i);
                        String sector = jobj.getString("business_sector");
                        String stage = jobj.getString("business_stage");
                        String state = jobj.getString("state");
                        Log.i("sector",sector+""+stage+""+state);
                        if(sector.equalsIgnoreCase(business_sector) && stage.equalsIgnoreCase(business_stage) && state.equalsIgnoreCase(business_state))
                        {
                            Log.i("object",jobj.toString());
                            queriedobjectsarray.put(jobj);
                        }

                    }
                    Log.i("query",queriedobjectsarray.toString());
                    for(int j=0;j<queriedobjectsarray.length();j++)
                    {
                        JSONObject queryobj = queriedobjectsarray.getJSONObject(j);
                        JSONArray services_provided = queryobj.getJSONArray("services_provided");
                        Log.i("services_provided",services_provided.toString());
                        for(int a = 0;a<services_provided.length();a++)
                        {
                            JSONObject subobj = services_provided.getJSONObject(a);
                            JSONArray sub_services_array = subobj.getJSONArray("sub_services");
                            Log.i("sub_services_array",sub_services_array.toString());
                            String nameofservice = subobj.getString("nameofservice");
                            Chip chip = new Chip(nameofservicechipgroup.getContext());
                            chip.setText(nameofservice);
                            chip.setCheckable(true);
                            chip.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                            chip.setElevation(10f);
                            chip.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            nameofservicechipgroup.addView(chip);

                            for(int b = 0;b<sub_services_array.length();b++)
                            {
                                JSONObject subserviceobj = sub_services_array.getJSONObject(b);
                                String sub_servicename = subserviceobj.getString("sub_service_name");
                                String link = subserviceobj.getString("link");
                                subserviceslist.add(new SubServicesInfo(nameofservice,sub_servicename,link));
                            }
                        }
                        Log.i("subservicelist",subserviceslist.toString());
                    }
                    Log.i("subservicelist2",subserviceslist.toString());
                    BusinessAdapter businessAdapter = new BusinessAdapter(BusinessService.this,subserviceslist);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BusinessService.this,LinearLayoutManager.VERTICAL,false);
                    subservicerecyclerview.setLayoutManager(linearLayoutManager);
                    BusinessItemDecorator businessItemDecorator = new BusinessItemDecorator(20);
                    subservicerecyclerview.addItemDecoration(businessItemDecorator);
                    subservicerecyclerview.setAdapter(businessAdapter);

                    //data entered in list successfully;
                    nameofservicechipgroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(ChipGroup group, int checkedId) {
                            chipselectedlist.clear();
                            int id = group.getCheckedChipId();
                            Chip chip = findViewById(id);
                            String chiptext = chip.getText().toString();
                            for(SubServicesInfo subServicesInfo: subserviceslist)
                            {
                                if(subServicesInfo.getNameofservice().equalsIgnoreCase(chiptext))
                                {
                                    chipselectedlist.add(subServicesInfo);
                                }
                            }
                            Log.i("chipselectedlist",chipselectedlist.toString());
                            //data from selected chip fetched;
                            BusinessAdapter businessAdapter1 = new BusinessAdapter(BusinessService.this,chipselectedlist);
                            subservicerecyclerview.setAdapter(businessAdapter1);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}