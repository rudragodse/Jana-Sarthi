package com.example.janasarthi.KaushalSarthi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.janasarthi.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KaushalSarthiHomePage extends AppCompatActivity {

    String[]Sectors;
    List<SectorInfo> sectorInfoList;
    RecyclerView sectorrecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaushal_sarthi_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Sectors = getResources().getStringArray(R.array.Sectors);
        sectorInfoList = new ArrayList<>();

        sectorInfoList.add(new SectorInfo("Aerospace and Aviation",R.mipmap.airplane));
        sectorInfoList.add(new SectorInfo("Agriculture",R.mipmap.agriculture));
        sectorInfoList.add(new SectorInfo("Apparel",R.mipmap.clothes));
        sectorInfoList.add(new SectorInfo("Automotive",R.mipmap.automotive));
        sectorInfoList.add(new SectorInfo("Beauty and Wellness",R.mipmap.beauty_and_wellness));
        sectorInfoList.add(new SectorInfo("BFSI(Banking, Finance Service and Insurance)",R.mipmap.banking_finance));
        sectorInfoList.add(new SectorInfo("Capital Goods and Manufacturing",R.mipmap.production));
        sectorInfoList.add(new SectorInfo("Construction",R.mipmap.construction));
        sectorInfoList.add(new SectorInfo("Chemicals and Petrochemicals",R.mipmap.petrochemicals));
        sectorInfoList.add(new SectorInfo("Education,Training and Research",R.mipmap.mentor_1));
        sectorInfoList.add(new SectorInfo("Power",R.mipmap.electric_power));
        sectorInfoList.add(new SectorInfo("Electronics and HW",R.mipmap.electric_power));
        sectorInfoList.add(new SectorInfo("IT and ITES",R.mipmap.computer));
        sectorInfoList.add(new SectorInfo("Environmental Science",R.mipmap.agriculture));
        sectorInfoList.add(new SectorInfo("Food Industry",R.mipmap.food_industry));
        sectorInfoList.add(new SectorInfo("Tourism and Hospitality",R.mipmap.tourism_and_hospitality));
        sectorInfoList.add(new SectorInfo("Office Administration and Facility Management",R.mipmap.office_administration));
        sectorInfoList.add(new SectorInfo("Handicrafts and Carpets",R.mipmap.handicrafts));
        sectorInfoList.add(new SectorInfo("Healthcare",R.mipmap.logo));
        sectorInfoList.add(new SectorInfo("Leather",R.mipmap.leather_goods));
        sectorInfoList.add(new SectorInfo("Media and Entertainment",R.mipmap.media_entertainment));
        sectorInfoList.add(new SectorInfo("Mining",R.mipmap.mining));
        sectorInfoList.add(new SectorInfo("Plumbing",R.mipmap.plumbing));
        sectorInfoList.add(new SectorInfo("Safety and Security",R.mipmap.security));
        sectorInfoList.add(new SectorInfo("Private Security",R.mipmap.security));
        sectorInfoList.add(new SectorInfo("Rubber Industry",R.mipmap.rubber_industry));
        sectorInfoList.add(new SectorInfo("Textile and Handloom",R.mipmap.textile));

        sectorrecyclerview = findViewById(R.id.sectorrecyclerview);
        SectorRecyclerAdapter sectorRecyclerAdapter = new SectorRecyclerAdapter(sectorInfoList,KaushalSarthiHomePage.this);
        sectorrecyclerview.setLayoutManager(new GridLayoutManager(KaushalSarthiHomePage.this,2));
        KaushalSarthiItemDecoration kaushalSarthiItemDecoration = new KaushalSarthiItemDecoration(20);
        sectorrecyclerview.addItemDecoration(kaushalSarthiItemDecoration);
        sectorrecyclerview.setAdapter(sectorRecyclerAdapter);
    }
}