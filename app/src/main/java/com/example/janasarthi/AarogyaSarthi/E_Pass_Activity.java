package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.example.janasarthi.Users.User;
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

public class E_Pass_Activity extends AppCompatActivity {

    DatabaseReference mref;
    DatabaseReference userref;
    FirebaseDatabase mdatabase;
    FirebaseAuth mauth;
    Spinner e_pass_state_spinner;
    RecyclerView services_recyclerview;
    JSONArray servicesarray;
    List<EPassInfo> servicelist;
    List<EPassInfo>receivedlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e__pass_);
        Objects.requireNonNull(getSupportActionBar()).hide();
        servicesarray = new JSONArray();
        receivedlist = new ArrayList<>();


        mdatabase = FirebaseDatabase.getInstance();
        mref = mdatabase.getReference("E-Pass Services");
        userref = mdatabase.getReference("Users");
        mauth = FirebaseAuth.getInstance();

        e_pass_state_spinner = findViewById(R.id.e_pass_state_spinner);
        services_recyclerview = findViewById(R.id.services_recyclerview);

        final String username = Objects.requireNonNull(mauth.getCurrentUser()).getDisplayName();
        userref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert username != null;
                User state= Objects.requireNonNull(snapshot.child(username).getValue(User.class));
                Log.i("state",state.getState());
//                getServices(state.getState());
                Log.i("receivedlist",receivedlist.toString());
                for(int i=0;i<getResources().getStringArray(R.array.states).length;i++)
                {
                    if(getResources().getStringArray(R.array.states)[i].equals(state.getState()))
                    {
                        e_pass_state_spinner.setSelection(i);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        e_pass_state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecteditem = adapterView.getSelectedItem().toString();
                getServices(selecteditem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });








    }

    private void getServices(final String state) {
        servicelist = new ArrayList<>();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object ob = snapshot.getValue();
                String json = new Gson().toJson(ob);
                try {
                    JSONObject jo = new JSONObject(json);
                    servicesarray = jo.getJSONArray("E-pass Services");
                    for(int i=0;i<servicesarray.length();i++)
                    {
                        JSONObject serviceobj = servicesarray.getJSONObject(i);
                        String objstate = serviceobj.getString("state");
                        if(objstate.equals(state))
                        {
                            JSONArray innerservicearray= serviceobj.getJSONArray("services");
                            for(int j=0;j<innerservicearray.length();j++)
                            {
                                JSONObject innerobj = innerservicearray.getJSONObject(j);
                                String servicename = innerobj.getString("service");
                                String link = innerobj.getString("link");
                                servicelist.add(new EPassInfo(servicename,link));
                            }
                        }

                    }
                    Log.i("servicelist",String.valueOf(servicelist.size()));
                    if(servicelist.size()==0)
                    {
                        Toast.makeText(E_Pass_Activity.this, "No services available for "+state, Toast.LENGTH_SHORT).show();
                    }
                    EPassAdapter ePassAdapter = new EPassAdapter(servicelist,E_Pass_Activity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(E_Pass_Activity.this,LinearLayoutManager.VERTICAL,false);
                    services_recyclerview.setLayoutManager(linearLayoutManager);
                    ItemDecorator itemDecorator = new ItemDecorator(20);
                    services_recyclerview.addItemDecoration(itemDecorator);
                    services_recyclerview.setAdapter(ePassAdapter);


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