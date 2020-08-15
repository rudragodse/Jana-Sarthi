package com.example.janasarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janasarthi.AarogyaSarthi.AarogyaSarthiHomePage;
import com.example.janasarthi.DhanSarthi.DhanSarthiHomePage;
import com.example.janasarthi.KaushalSarthi.KaushalSarthiHomePage;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class JanaSarthiHomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    ImageView Navigationopenerimgview;
    DrawerLayout navigationdrawerlayout;
    FirebaseAuth mauth;
    MaterialCardView mainmodulecardview,secondarymodulecardview,tertiarymodulecardview;
    MaterialButton mainmoduleexplorebtn,secondarymoduleexplorebtn,tertiarymoduleexplorebtn;
    TextView mainmoduletitletextview,mainmoduledesctextview;
    TextView secondarymoduletitletextview,secondarymoduledesctextview;
    TextView tertiarymoduletitletextview,tertiarymoduledesctextview;
    NavigationView navigationView;

    FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
    DatabaseReference mrefrence = mdatabase.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jana_sarthi_home_page);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Navigationopenerimgview = findViewById(R.id.navigationopenerimgview);
        navigationdrawerlayout = findViewById(R.id.navigationdrawerlayout);
        navigationView = findViewById(R.id.navigationview);
        View headerview = navigationView.getHeaderView(0);
        TextView useremailtxtview = headerview.findViewById(R.id.useremailtextview);
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        assert user != null;
        String email = user.getEmail();
        useremailtxtview.setText(email);


        Navigationopenerimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!navigationdrawerlayout.isDrawerOpen(GravityCompat.START))
                {
                    navigationdrawerlayout.openDrawer(GravityCompat.START);
                }
                else
                {
                    navigationdrawerlayout.closeDrawer(GravityCompat.END);
                }
            }
        });



        navigationView.setNavigationItemSelectedListener(JanaSarthiHomePageActivity.this);




















        //main module card:
        mainmodulecardview = findViewById(R.id.mainmodulecardview);
        mainmoduletitletextview = findViewById(R.id.mainmodulenametextview);
        mainmoduledesctextview = findViewById(R.id.mainmoduledesctextview);
        mainmoduleexplorebtn = findViewById(R.id.mainmoduleexplorebtn);

        mainmoduleexplorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JanaSarthiHomePageActivity.this, AarogyaSarthiHomePage.class);
                startActivity(intent);
            }
        });
        //end main module card


        //secondary module card:
        secondarymodulecardview = findViewById(R.id.secondarymodulecardview);
        secondarymoduletitletextview = findViewById(R.id.secondarymoduletitletxtview);
        secondarymoduledesctextview = findViewById(R.id.secondarymoduledesctextview);
        secondarymoduleexplorebtn = findViewById(R.id.secondarymoduleexplorebtn);

        secondarymoduleexplorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JanaSarthiHomePageActivity.this, KaushalSarthiHomePage.class);
                startActivity(intent);
            }
        });
        //end secondary module card:

        //tertiary module card:
        tertiarymodulecardview = findViewById(R.id.tertiarymodulecardview);
        tertiarymoduletitletextview = findViewById(R.id.tertiarymoduletitletextview);
        tertiarymoduledesctextview = findViewById(R.id.tertiarymoduledesctextview);
        tertiarymoduleexplorebtn = findViewById(R.id.tertiarymoduleexplorebtn);

        tertiarymoduleexplorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JanaSarthiHomePageActivity.this, DhanSarthiHomePage.class);
                startActivity(intent);
            }
        });
        //end tertiary card;






    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {

            case R.id.manage_profile_item:
                Toast.makeText(JanaSarthiHomePageActivity.this, "Your clicked manage profile", Toast.LENGTH_SHORT).show();
                break;


            case R.id.logout_item:
                mauth.signOut();
                Intent intent = new Intent(JanaSarthiHomePageActivity.this,SignInActivity.class);
                startActivity(intent);
                break;
        }


        return true;
    }
}