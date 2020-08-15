package com.example.janasarthi.AarogyaSarthi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.janasarthi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Labs extends AppCompatActivity implements labsresponse {

    JSONArray locTestLabs;
    String usercity;
    ArrayList<LabsFormat>labinfo;
    RecyclerView resultsrecyclerview;
    RecyclerViewAdapter resultsrecyclerViewAdapter;
    TextView usercitytxtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labs);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = getIntent();
        usercity = intent.getStringExtra("cityname");
        usercitytxtview = findViewById(R.id.usercity);
        usercitytxtview.setText(usercity);
        resultsrecyclerview = findViewById(R.id.results_recyclerview);

        locTestLabs = new JSONArray();
        labinfo = new ArrayList<>();

        FetchLabsData fetchLabsData = new FetchLabsData();
        fetchLabsData.delegate= (labsresponse) this;
        fetchLabsData.execute();
    }

    @Override
    public void labsresponseFinish(JSONArray jsonArray) {
        Log.i("Jsonfromlabs",jsonArray.toString());
        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                String category = object.getString("category");
                String city = object.getString("city");

                if (city.equals(usercity) && category.equals("CoVID-19 Testing Lab"))
                {
                    locTestLabs.put(object);
//                    Log.i("loc",locTestLabs.toString());
                }

            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.i("allPune",locTestLabs.toString());
        for(int i=0;i<locTestLabs.length();i++)
        {
            try{
                JSONObject obj1 = locTestLabs.getJSONObject(i);
                Log.i("obj1",obj1.toString());
                String orgname = obj1.getString("nameoftheorganisation");
                String cityname = obj1.getString("city");
                String phonenumber = obj1.getString("phonenumber");
                String category = obj1.getString("category");

                LabsFormat labsFormat = new LabsFormat();
                labsFormat.setCity(cityname);
                labsFormat.setNameoforganization(orgname);
                labsFormat.setPhonenumber(phonenumber);
                labsFormat.setCategory(category);
                Log.i("labformat",labsFormat.getNameoforganization());
                labinfo.add(labsFormat);
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
        Log.i("labinfosize",String.valueOf(labinfo.size()));
        resultsrecyclerViewAdapter = new RecyclerViewAdapter(labinfo,Labs.this);
        resultsrecyclerview.setAdapter(resultsrecyclerViewAdapter);
        resultsrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        ItemDecorator itemDecorator = new ItemDecorator(60);
        resultsrecyclerview.addItemDecoration(itemDecorator);
    }
}