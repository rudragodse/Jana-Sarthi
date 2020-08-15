package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class TakeTest extends AppCompatActivity {

    public Boolean ischeckable = false;
    Dialog reportDialog;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence;
    FirebaseAuth mauth;

    //    Cough chips declaration:
    Chip cough_yesoption;
    Chip cough_nooption;

    Chip coughday1;
    Chip coughday2;
    Chip coughday3;
    Chip coughday4;
    Chip coughday5;

    ViewGroup coughdaysviewgroup;
//    Cough chip declaration ends:

    //    Fever Chips declaration:
    Chip fever_yesoption;
    Chip fever_nooption;

    Chip feverday1;
    Chip feverday2;
    Chip feverday3;
    Chip feverday4;
    Chip feverday5;

    ViewGroup feverdaysviewgroup;
//    Fever Chips Declaration:

    //    headache Chips Declaration:
    Chip breathing_nooption;
    Chip breathing_yesoption;

    Chip breathingday1;
    Chip breathingday2;
    Chip breathingday3;
    Chip breathingday4;
    Chip breathingday5;

    ViewGroup breathingdaysviewgroup;


    public void renderReport(View view){
        TextView closebtntext;
        MaterialButton searchforbtn;
        ImageView fit_or_sick;
        TextView desc;
        reportDialog.setContentView(R.layout.popup_report);
        closebtntext = reportDialog.findViewById(R.id.closebtn_text);
        searchforbtn = reportDialog.findViewById(R.id.searchforbtn);
        fit_or_sick = reportDialog.findViewById(R.id.fit_or_sick_image);
        desc = reportDialog.findViewById(R.id.desc);
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Users");
        closebtntext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportDialog.dismiss();
            }
        });

//        if((feverday1.isChecked()||feverday2.isChecked()||feverday3.isChecked()||feverday4.isChecked()||feverday5.isChecked()) ||
//                (coughday1.isChecked()||coughday2.isChecked()||coughday3.isChecked()||coughday4.isChecked()||coughday5.isChecked()) ||
//                (headacheday1.isChecked()||headacheday2.isChecked()||headacheday3.isChecked()||headacheday4.isChecked()||headacheday5.isChecked()))
//        {
//            Toast.makeText(TakeTest.this, "Please deselect the days options as you have selected no option for symptoms", Toast.LENGTH_SHORT).show();
//        }

        if((!fever_yesoption.isChecked() && !fever_nooption.isChecked()) &&
                (!cough_yesoption.isChecked() && !cough_nooption.isChecked()) &&
                (!breathing_yesoption.isChecked() && !breathing_nooption.isChecked()))
        {
            Toast.makeText(TakeTest.this,"You have not selected any of the options",Toast.LENGTH_SHORT).show();
        }
        else if(fever_yesoption.isChecked() && cough_yesoption.isChecked() && breathing_yesoption.isChecked())
        {
            desc.setText("You are at a risk of contacting the virus, for better results please select the days options also");
            fit_or_sick.setImageResource(R.drawable.sick);
            reportDialog.show();
        }
        if(fever_nooption.isChecked() && cough_nooption.isChecked() && breathing_nooption.isChecked())
        {

//            if (((feverday1.isCheckable())==ischeckable&&(feverday2.isCheckable())==ischeckable&&(feverday3.isCheckable())==ischeckable&&(feverday4.isCheckable())==ischeckable&&(feverday5.isCheckable())==ischeckable) ||
//                    ((coughday1.isCheckable())==ischeckable&&(coughday2.isCheckable())==ischeckable&&(coughday3.isCheckable())==ischeckable&&(coughday4.isCheckable())==ischeckable&&(coughday5.isCheckable())==ischeckable) ||
//                    ((headacheday1.isCheckable())==ischeckable&&(headacheday2.isCheckable())==ischeckable&&(headacheday3.isCheckable())==ischeckable&&(headacheday4.isCheckable())==ischeckable&&(headacheday5.isCheckable())==ischeckable))
//            {
//                feverday1.setCheckable(!ischeckable);
//                feverday2.setCheckable(!ischeckable);
//                feverday3.setCheckable(!ischeckable);
//                feverday4.setCheckable(!ischeckable);
//                feverday5.setCheckable(!ischeckable);
//
//                coughday1.setCheckable(!ischeckable);
//                coughday2.setCheckable(!ischeckable);
//                coughday3.setCheckable(!ischeckable);
//                coughday4.setCheckable(!ischeckable);
//                coughday5.setCheckable(!ischeckable);
//
//                headacheday1.setCheckable(!ischeckable);
//                headacheday2.setCheckable(!ischeckable);
//                headacheday3.setCheckable(!ischeckable);
//                headacheday4.setCheckable(!ischeckable);
//                headacheday5.setCheckable(!ischeckable);
//
//            }
            feverdaysviewgroup.setClickable(false);
            coughdaysviewgroup.setClickable(false);
            breathingdaysviewgroup.setClickable(false);

            desc.setText("You are Safe and Healthy. \n Keep Up the good work and keep excersizing");
            reportDialog.show();
        }
        else if((fever_yesoption.isChecked() && feverday1.isChecked()) &&
                (cough_yesoption.isChecked() && coughday1.isChecked()) &&
                (breathing_yesoption.isChecked() && breathingday1.isChecked()))
        {
            desc.setText("You are running a slight risk of contacting the virus. \n If symptoms worsen in the upcoming days visit the doctor");
            fit_or_sick.setImageResource(R.drawable.sick);
            searchforbtn.setVisibility(View.VISIBLE);
            searchforbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=medical stores");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }
            });

            reportDialog.show();
            //            Firebase database code
            final FirebaseUser user = mauth.getCurrentUser();
            assert user != null;
            String email = user.getEmail();
            final String username = user.getDisplayName() ;

//            TestResults testResults = new TestResults(user.getEmail(),"Slight Risk");
//            mrefrence.child(user.getUid()).setValue(testResults);
//            new shitty code;
            Log.i("Firebaseuser", Objects.requireNonNull(user.getDisplayName()));
            mrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    assert username != null;
                    if(!dataSnapshot.child(username).hasChild(("covid test")))
                    {
                        mrefrence.child(username).child("covid test").setValue("Slight Risk");
                    }
                    else
                    {
                        mrefrence.child(username).child("covid test").setValue("Slight risk");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
//            new shitty code end;


//            end here:
        }
        else if ((fever_yesoption.isChecked() && (feverday2.isChecked() || feverday3.isChecked() || feverday4.isChecked())) &&
                (cough_yesoption.isChecked() && (coughday2.isChecked() || coughday3.isChecked() || coughday4.isChecked())) &&
                breathing_yesoption.isChecked() && (breathingday2.isChecked() || breathingday3.isChecked() || breathingday4.isChecked()))
        {
            desc.setText("You are at medium risk of contatcting the virus. \n We suggest you get yourself checked at neerest clinic");
            fit_or_sick.setImageResource(R.drawable.sick);
            searchforbtn.setVisibility(View.VISIBLE);
            searchforbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=medical stores");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }
            });
            reportDialog.show();
            //            Firebase database code
            FirebaseUser user = mauth.getCurrentUser();
            assert user != null;
            String email = user.getEmail();
            final String username = user.getDisplayName();
//            TestResults testResults = new TestResults(user.getEmail(),"Medium Risk");
//            mrefrence.child(user.getUid()).setValue(testResults);
            //shitty code that works
            mrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    assert username != null;
                    if(!dataSnapshot.child(username).hasChild(("covid test")))
                    {
                        mrefrence.child(username).child("covid test").setValue("Medium Risk");
                    }
                    else
                    {
                        mrefrence.child(username).child("covid test").setValue("Medium Risk");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            // shitty code that ends
//            end here:
        }
        else if((fever_yesoption.isChecked() && feverday5.isChecked()) &&
                (cough_yesoption.isChecked() && coughday5.isChecked()) &&
                (breathing_yesoption.isChecked() && breathingday5.isChecked()))
        {
            desc.setText("You are at high risk of contacting the virus. \n We recommend you get yourself admitted");
            fit_or_sick.setImageResource(R.drawable.sick);
            searchforbtn.setVisibility(View.VISIBLE);
            searchforbtn.setText("SEARCH FOR HOSPITALS");
            searchforbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }
            });
            reportDialog.show();
            //            Firebase database code
            FirebaseUser user = mauth.getCurrentUser();
            assert user != null;
            String email = user.getEmail();
            final String username = user.getDisplayName();
//            TestResults testResults = new TestResults(user.getEmail(),"High Risk");
//            mrefrence.child(user.getUid()).setValue(testResults);
            //shitty code that works;
            mrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    assert username != null;
                    if(!dataSnapshot.child(username).hasChild(("covid test")))
                    {
                        mrefrence.child(username).child("covid test").setValue("High Risk");
                    }
                    else
                    {
                        mrefrence.child(username).child("covid test").setValue("High Risk");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //shitty code that ends here;
//            end here:
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);
        Objects.requireNonNull(getSupportActionBar()).hide();
        reportDialog = new Dialog(this);
        Objects.requireNonNull(reportDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        Cough Chip options:
        cough_yesoption = findViewById(R.id.cough_yesoption);
        cough_nooption = findViewById(R.id.cough_nooption);

        coughday1 = findViewById(R.id.cough_day_1);
        coughday2 = findViewById(R.id.cough_day_2);
        coughday3 = findViewById(R.id.cough_day_3);
        coughday4 = findViewById(R.id.cough_day_4);
        coughday5 = findViewById(R.id.cough_day_5);

        coughdaysviewgroup = findViewById(R.id.cough_daysviewgroup);
//        Cough chip options end:

//        Fever Chip options:
        fever_yesoption = findViewById(R.id.fever_yesoption);
        fever_nooption = findViewById(R.id.fever_nooption);

        feverday1 = findViewById(R.id.fever_day_1);
        feverday2 = findViewById(R.id.fever_day_2);
        feverday3 = findViewById(R.id.fever_day_3);
        feverday4 = findViewById(R.id.fever_day_4);
        feverday5 = findViewById(R.id.fever_day_5);

        feverdaysviewgroup = findViewById(R.id.fever_daysviewgroup);
//        Fever chip options end:

//        Breathing Chip options:
        breathing_yesoption = findViewById(R.id.breathing_yesoption);
        breathing_nooption = findViewById(R.id.breathing_nooption);

        breathingday1 = findViewById(R.id.breathing_day_1);
        breathingday2 = findViewById(R.id.breathing_day_2);
        breathingday3 = findViewById(R.id.breathing_day_3);
        breathingday4 = findViewById(R.id.breathing_day_4);
        breathingday5 = findViewById(R.id.breathing_day_5);

        breathingdaysviewgroup = findViewById(R.id.breathing_daysviewgroup);
//        Breathing Chip options end:

        mauth = FirebaseAuth.getInstance();

    }
}