package com.example.janasarthi.DhanSarthi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.janasarthi.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CovidEssentialProducts extends AppCompatActivity {

    RecyclerView essentialRecyclerview;
    List<CovidEssentialProduct>covidproductlist;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_essential_products);
        Objects.requireNonNull(getSupportActionBar()).hide();

        essentialRecyclerview = findViewById(R.id.essentials_recyclerview);
        covidproductlist = new ArrayList<>();
        String link = "https://mkp.gem.gov.in/registration/signup#!/seller";

        covidproductlist.add(new CovidEssentialProduct("Ventilators",link,R.drawable.icu));
        covidproductlist.add(new CovidEssentialProduct("Alcohol Sanitizer",link,R.drawable.alcohol_sanitizer));
        covidproductlist.add(new CovidEssentialProduct("Face Shield",link,R.drawable.face_shield));
        covidproductlist.add(new CovidEssentialProduct("N 95 mask",link,R.drawable.n95_mask));
        covidproductlist.add(new CovidEssentialProduct("Latex single use gloves",link,R.drawable.rubber_gloves));
        covidproductlist.add(new CovidEssentialProduct("Protective Gowns",link,R.drawable.gowns));
        covidproductlist.add(new CovidEssentialProduct("Disposable thermometers",link,R.drawable.disposablethermometer));
        covidproductlist.add(new CovidEssentialProduct("Medical Masks",link,R.drawable.face_mask));
        covidproductlist.add(new CovidEssentialProduct("Disinfectant",link,R.drawable.desinfectant));
        covidproductlist.add(new CovidEssentialProduct("Biohazard Bags",link,R.drawable.biohazard_bag));
        covidproductlist.add(new CovidEssentialProduct("Wheel Chairs",link,R.drawable.wheelchair));
        covidproductlist.add(new CovidEssentialProduct("IV Fluids-DNS",link,R.drawable.iv_fluid_1));
        covidproductlist.add(new CovidEssentialProduct("IV Fluids-Dextrose",link,R.drawable.iv_fluid_1));
        covidproductlist.add(new CovidEssentialProduct("IV Fluids-DNS",link,R.drawable.iv_bag));
        covidproductlist.add(new CovidEssentialProduct("Stretcher",link,R.drawable.icu));
        covidproductlist.add(new CovidEssentialProduct("Thermnal Scanners",link,R.drawable.thermometer));
        covidproductlist.add(new CovidEssentialProduct("Ambulance",link,R.drawable.ambulance));
        covidproductlist.add(new CovidEssentialProduct("First Aid kits",link,R.drawable.first_aid_kit));
        covidproductlist.add(new CovidEssentialProduct("Oxygen cylinders",link,R.drawable.portable_oxygen));
        covidproductlist.add(new CovidEssentialProduct("ICU Beds",link,R.drawable.hospital_beds));

        EssentialAdapter essentialAdapter = new EssentialAdapter(CovidEssentialProducts.this,covidproductlist);
        essentialRecyclerview.setLayoutManager(new GridLayoutManager(CovidEssentialProducts.this,2));
        essentialRecyclerview.addItemDecoration(new EssentialItemDecorator(15) );
        essentialRecyclerview.setAdapter(essentialAdapter);


    }
}