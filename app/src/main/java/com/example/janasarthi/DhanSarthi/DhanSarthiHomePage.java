package com.example.janasarthi.DhanSarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.Objects;

public class DhanSarthiHomePage extends AppCompatActivity {

    Spinner business_sector_spinner,business_stage_spinner,business_state_spinner;
    MaterialButton goahead_btn;
    String business_sector,business_stage,business_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhan_sarthi_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();



        goahead_btn = findViewById(R.id.goahead_btn);

        //1st spinner
        business_sector_spinner = findViewById(R.id.business_sector_spinner);
        ArrayAdapter<String>arrayAdapter = new ArrayAdapter<>(DhanSarthiHomePage.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.business_sector));
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        business_sector_spinner.setPrompt("select business sector..");
        business_sector_spinner.setAdapter(arrayAdapter);

        //1st on ItemSelectedlistner:
        business_sector_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("selecteditem",adapterView.getItemAtPosition(i).toString());
                if (adapterView.getItemAtPosition(i).toString().equals("select your business sector.."))
                {
                    goahead_btn.setClickable(false);
                }
                else
                {
                    goahead_btn.setClickable(true);
                    business_sector = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //2nd spinner
        business_stage_spinner = findViewById(R.id.business_stage_spinner);
        ArrayAdapter<String>arrayAdapter1 = new ArrayAdapter<>(DhanSarthiHomePage.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.business_stage));
        arrayAdapter1.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        business_stage_spinner.setPrompt("select your business stage..");
        business_stage_spinner.setAdapter(arrayAdapter1);

        business_stage_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("select your business stage.."))
                {
                    goahead_btn.setClickable(false);
                }
                else
                {
                    goahead_btn.setClickable(true);
                    business_stage = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //3rd spinner
        business_state_spinner = findViewById(R.id.business_state_spinner);
        ArrayAdapter<String>arrayAdapter2 = new ArrayAdapter<>(DhanSarthiHomePage.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.states));
        arrayAdapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        business_state_spinner.setPrompt("select your business state..");
        business_state_spinner.setAdapter(arrayAdapter2);

        business_state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("select another state or UT.."))
                {
                    goahead_btn.setClickable(false);
                }
                else
                {
                    goahead_btn.setClickable(true);
                    business_state = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        goahead_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("businesssector",business_sector);
                Log.i("businessstage",business_stage);
                Log.i("businessstate",business_state);

                if(business_sector.equalsIgnoreCase("Covid 19 Essential Products"))
                {
                    Intent intent = new Intent(DhanSarthiHomePage.this,CovidEssentialProducts.class);
                    startActivity(intent);
                }
                else
                {

                    if (business_state.equals("Maharashtra")) {
                        BusinessInfo businessInfo = new BusinessInfo(business_sector, business_stage, business_state);
                        Intent intent = new Intent(DhanSarthiHomePage.this, BusinessService.class)
                                .putExtra("BusinessInfo", businessInfo);
                        startActivity(intent);
                    } else {
                        business_state = "Other than Maharashtra";
                        BusinessInfo businessInfo = new BusinessInfo(business_sector, business_stage, "Other than Maharashtra");
                        Intent intent = new Intent(DhanSarthiHomePage.this, BusinessService.class)
                                .putExtra("BusinessInfo", businessInfo);
                        startActivity(intent);
                    }
                }
            }
        });




    }
}