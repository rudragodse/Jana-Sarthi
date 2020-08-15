package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.example.janasarthi.Users.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class GetHelplineNos extends AppCompatActivity implements Helplineresponse{

    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence;
    FirebaseUser user;
    TextView helplineuserstate,helplinenumbertext;
    Spinner stateselectorspinner;
    String spinnerselectedstate="";
    MaterialButton helplinecallbtn;
    JSONArray spinnerjsonarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_helpline_nos);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(GetHelplineNos.this,R.color.yellowtheme));

        helplineuserstate = findViewById(R.id.helplineuserstate);
        helplinenumbertext = findViewById(R.id.helplinenumbertxt);
        helplinecallbtn = findViewById(R.id.helplinecallbtn);
        spinnerjsonarray = new JSONArray();


        StatehelplineInfo statehelplineInfo = new StatehelplineInfo();
        statehelplineInfo.delegate = this;
        statehelplineInfo.execute();





        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Users");
        user = mauth.getCurrentUser();
        assert user != null;
        final String searchkey = user.getDisplayName();
        assert searchkey != null;
        mrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 User state= Objects.requireNonNull(snapshot.child(searchkey).getValue(User.class));
                Log.i("state",state.getState());
                helplineuserstate.setText(state.getState());
                String userstate = state.getState();
                Log.i("userstate",userstate);
                for(int i=0;i<spinnerjsonarray.length();i++)
                {
                    Log.i("for","inside for");
                    try {
                        JSONObject jo = spinnerjsonarray.getJSONObject(i);
                        String loc = jo.getString("loc");
                        final String number = jo.getString("number");
                        if(loc.equals(userstate))
                        {
                            Log.i("if","inside if");
                            helplineuserstate.setText(loc);
                            helplinenumbertext.setText(number);
                            helplinecallbtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Uri uri= Uri.parse("tel:"+number);
                                    Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                                    startActivity(intent);
                                }
                            });

                        }
                        else
                        {
                            Log.i("else","failed");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        stateselectorspinner = findViewById(R.id.stateselectorspinner);
        stateselectorspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerselectedstate = adapterView.getSelectedItem().toString();
                for(int j=0;j<spinnerjsonarray.length();j++)
                {
                    try {
                        JSONObject jo = spinnerjsonarray.getJSONObject(j);
                        String loc = jo.getString("loc");
                        final String number = jo.getString("number");
                        if(loc.equals(spinnerselectedstate))
                        {
                            helplineuserstate.setText(loc);
                            helplinenumbertext.setText(number);
                            helplinecallbtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Uri uri= Uri.parse("tel:"+number);
                                    Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                                    startActivity(intent);
                                }
                            });

                        }
                        else
                        {
                            Log.i("else","failed");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                Toast.makeText(GetHelplineNos.this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }

    @Override
    public void HelplineResponseFinish(JSONArray jsonArray) throws JSONException {
        Log.i("Got json",jsonArray.toString());
        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject jo = jsonArray.getJSONObject(i);
            spinnerjsonarray.put(jo);
//
        }
    }
}