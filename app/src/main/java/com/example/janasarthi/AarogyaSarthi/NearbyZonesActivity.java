package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.janasarthi.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class NearbyZonesActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback,locationCallback1;
    TextView loc_cord;
    LottieAnimationView animationView;
    RecyclerView zonesrecyclerview;
    ZonesRecyclerAdapter zonesRecyclerAdapter;
    Chip five_hundred_chip,thousand_chip,fifteenhundred_chip,twothousand_chip;
    ChipGroup distanceChipGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_zones);

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(NearbyZonesActivity.this,R.color.zonecolor));

        Objects.requireNonNull(getSupportActionBar()).hide();
        loc_cord = findViewById(R.id.loc_cord);
        animationView = findViewById(R.id.lottie_animation);
        zonesrecyclerview = findViewById(R.id.ZonesRecyclerView);
        five_hundred_chip = findViewById(R.id.fivehundredchip);
        thousand_chip  = findViewById(R.id.thousandchip);
        fifteenhundred_chip = findViewById(R.id.fifteenhundredchip);
        twothousand_chip = findViewById(R.id.twothousandchip);
        distanceChipGroup = findViewById(R.id.distance_chipGroup);







        if(ContextCompat.checkSelfPermission(NearbyZonesActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(NearbyZonesActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }
        else
        {
            getCurrentLocation();
        }


    }

    private void getCurrentLocation()
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if(ContextCompat.checkSelfPermission(NearbyZonesActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(NearbyZonesActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            getCurrentLocation();
        }
        LocationServices.getFusedLocationProviderClient(NearbyZonesActivity.this)
                .requestLocationUpdates(locationRequest,new LocationCallback(){

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(NearbyZonesActivity.this)
                                .removeLocationUpdates(this);
                        if(locationResult!=null && locationResult.getLocations().size()>0)
                        {
                            int latestlocationindex = locationResult.getLocations().size()-1;
                            final double latitude = locationResult.getLocations().get(latestlocationindex).getLatitude();
                            final double longitude = locationResult.getLocations().get(latestlocationindex).getLongitude();
                            Log.i("LOCA",latitude+""+longitude);
                            Geocoder geocoder = new Geocoder(NearbyZonesActivity.this);
                            try {
                                List<Address>addressList = geocoder.getFromLocation(latitude,longitude,1);
                                if(addressList!=null && addressList.size()>0)
                                {
                                    Log.i("NZA","addresslist not null");
                                    Address address = addressList.get(0);
                                    Log.i("ADDR",address.getAddressLine(0));
                                    loc_cord.setText(address.getAddressLine(0));
                                    animationView.setVisibility(View.GONE);

                                    distanceChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(ChipGroup group, int checkedId) {
                                            Chip chip = group.findViewById(checkedId);
                                            if(chip.getText().equals("500m"))
                                            {
                                                GeoIqRequestObject geoIqRequestObject = new GeoIqRequestObject("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtYWlsSWRlbnRpdHkiOiJydWRyYS5nb2RzZUBnbWFpbC5jb20ifQ.AW25mnAsfK4gsy4wa_1Q8OW8Y8dF_ekofBVcyNzuaq0",
                                                        longitude,latitude,500);
                                                sendRequestObject(geoIqRequestObject);
                                            }
                                            if (chip.getText().equals("1000m"))
                                            {
                                                Log.i("thousand","checked");
                                                GeoIqRequestObject geoIqRequestObject = new GeoIqRequestObject("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtYWlsSWRlbnRpdHkiOiJydWRyYS5nb2RzZUBnbWFpbC5jb20ifQ.AW25mnAsfK4gsy4wa_1Q8OW8Y8dF_ekofBVcyNzuaq0",
                                                        longitude,latitude,1000);
                                                sendRequestObject(geoIqRequestObject);
                                            }
                                            if(chip.getText().equals("1500m"))
                                            {
                                                Log.i("fifteenhundred","clicked");
                                                GeoIqRequestObject geoIqRequestObject = new GeoIqRequestObject("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtYWlsSWRlbnRpdHkiOiJydWRyYS5nb2RzZUBnbWFpbC5jb20ifQ.AW25mnAsfK4gsy4wa_1Q8OW8Y8dF_ekofBVcyNzuaq0",
                                                        longitude,latitude,1500);
                                                sendRequestObject(geoIqRequestObject);
                                            }
                                            if (chip.getText().equals("2000m"))
                                            {
                                                GeoIqRequestObject geoIqRequestObject = new GeoIqRequestObject("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtYWlsSWRlbnRpdHkiOiJydWRyYS5nb2RzZUBnbWFpbC5jb20ifQ.AW25mnAsfK4gsy4wa_1Q8OW8Y8dF_ekofBVcyNzuaq0",
                                                        longitude,latitude,2000);
                                                sendRequestObject(geoIqRequestObject);
                                            }

                                        }
                                    });



                                    GeoIqRequestObject geoIqRequestObject = new GeoIqRequestObject("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtYWlsSWRlbnRpdHkiOiJydWRyYS5nb2RzZUBnbWFpbC5jb20ifQ.AW25mnAsfK4gsy4wa_1Q8OW8Y8dF_ekofBVcyNzuaq0",
                                            longitude,latitude,500
                                            );


//                                    JSONObject geoiqobject1 = new JSONObject();
//                                    geoiqobject1.put("key","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtYWlsSWRlbnRpdHkiOiJydWRyYS5nb2RzZUBnbWFpbC5jb20ifQ.AW25mnAsfK4gsy4wa_1Q8OW8Y8dF_ekofBVcyNzuaq0");
//                                    geoiqobject1.put("lng",longitude);
//                                    geoiqobject1.put("lat",latitude);
//                                    geoiqobject1.put("radius",2000);
//
//                                    Log.i("OBJTOSEND",geoiqobject1.toString());


                                    sendRequestObject(geoIqRequestObject);

//

                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },Looper.getMainLooper());

    }

    private void sendRequestObject(final GeoIqRequestObject geoIqRequestObject)
    {
            Log.i("geoiq",geoIqRequestObject.toString());
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://data.geoiq.io/dataapis/v1.0/covid/")
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();

            //getclient and call object for request;
            NearbyZonesRequest nearbyZonesRequest = retrofit.create(NearbyZonesRequest.class);
            Call<JsonObject> call = nearbyZonesRequest.retreiveZones(geoIqRequestObject);

            Log.i("CALL",call.toString());


            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    Toast.makeText(NearbyZonesActivity.this, "Yeah Zones Received", Toast.LENGTH_SHORT).show();
                    Log.i("RESPONSE", String.valueOf(response.body()));
                    Log.i("RESPONSECODE",String.valueOf(response.code()));
                    Log.i("RESPONSEVAL",new Gson().toJson(response.body()));
                    try {
                        JSONObject jo = new JSONObject(new Gson().toJson(response.body()));
                        Log.i("JO",jo.toString());
                        String cavail = jo.getString("containmentsAvailability");
                        Log.i("cavail",cavail);
                        JSONArray ja = jo.getJSONArray("containmentZoneNames");
                        List<String>czones = new ArrayList<>();
                        for(int i=0;i<ja.length();i++)
                        {
                            czones.add(ja.getString(i));
                        }
                        zonesRecyclerAdapter = new ZonesRecyclerAdapter(czones,NearbyZonesActivity.this);
                        zonesrecyclerview.setAdapter(zonesRecyclerAdapter);
                        zonesrecyclerview.setLayoutManager(new LinearLayoutManager(NearbyZonesActivity.this));
                        ItemDecorator itemDecorator = new ItemDecorator(20);
                        zonesrecyclerview.addItemDecoration(itemDecorator);
                        Log.i("CZONE",czones.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    assert response.body() != null;
                    Log.i("RESPONSESUCESS", String.valueOf(response.isSuccessful()));

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(NearbyZonesActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1 && grantResults.length>0)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
        }
    }


}
