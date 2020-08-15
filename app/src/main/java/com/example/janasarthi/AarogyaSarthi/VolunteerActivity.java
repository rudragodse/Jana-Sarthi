package com.example.janasarthi.AarogyaSarthi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.janasarthi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class VolunteerActivity extends AppCompatActivity implements donationsresponse {

    TextView usercityforfood;
    RecyclerView resultsforfood_recyclerview;
    JSONArray locfreefooddonations;
    ArrayList<DonationInfo> donationInfoArrayList;
    DonationsRecyclerAdapter donationsrecyclerviewadapter;
    String citynameforfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //activity full screen;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_volunteer);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = getIntent();
        citynameforfood = intent.getStringExtra("cityname");

        usercityforfood = findViewById(R.id.usercityforfood);
        usercityforfood.setText(citynameforfood);

        //recycler view;
        resultsforfood_recyclerview = findViewById(R.id.resultsforfood_recyclerview);


        //json array;
        locfreefooddonations = new JSONArray();
        donationInfoArrayList = new ArrayList<>();

        FetchDonationsInfo fetchDonationsInfo= new FetchDonationsInfo();
        fetchDonationsInfo.delegate = (donationsresponse) VolunteerActivity.this;
        fetchDonationsInfo.execute();






    }


    @Override
    public void donationsresponsefinish(JSONArray jsonArray) {
        Log.i("Jsonfromprocess",jsonArray.toString());
        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                String category = object.getString("category");
                String city = object.getString("city");

                if (city.equals(citynameforfood) && category.equals("Free Food"))
                {
                    Log.i("insideiffree","true");
                    locfreefooddonations.put(object);
                    Log.i("locfreefood",locfreefooddonations.toString());
                }

            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.i("allPune",locfreefooddonations.toString());
        for(int i=0;i<locfreefooddonations.length();i++)
        {
            try{
                JSONObject obj1 = locfreefooddonations.getJSONObject(i);
                Log.i("obj1",obj1.toString());
                String orgname = obj1.getString("nameoftheorganisation");
                String cityname = obj1.getString("city");
                String phonenumber = obj1.getString("phonenumber");
                String category = obj1.getString("category");
                String description = obj1.getString("descriptionandorserviceprovided");

                DonationInfo donationInfo = new DonationInfo();
                donationInfo.setTitleofOrg(orgname);
                donationInfo.setContact(phonenumber);
                donationInfo.setCategory(category);
                donationInfo.setDesc(description);
                Log.i("donationformat",donationInfo.getTitleofOrg());
                donationInfoArrayList.add(donationInfo);
//                Log.i("labinfo",labinfo.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        for(int i=0;i<labinfo.size();i++)
//        {
//            Log.i("labinfo", (labinfo.get(i)).getNameoforganization());
////            Send data through here;
//
//        }
        Log.i("labinfosize",String.valueOf(donationInfoArrayList.size()));
        donationsrecyclerviewadapter = new DonationsRecyclerAdapter(donationInfoArrayList,VolunteerActivity.this);
        resultsforfood_recyclerview.setAdapter(donationsrecyclerviewadapter);
        resultsforfood_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        ItemDecorator itemDecorator = new ItemDecorator(60);
        resultsforfood_recyclerview.addItemDecoration(itemDecorator);
    }
}