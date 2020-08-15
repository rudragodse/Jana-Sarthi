package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.os.Build.VERSION_CODES.O;

public class AarogyaSarthiHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager cardViewPager;
    Adapter adapter;
    List<Model> models;
    private TextView[]mDots;
    LinearLayout dotlayout;
    MaterialButton TakeTestButton;
    NavigationView navigationView;
    ImageView navigationopenerimgview;
    DrawerLayout aarogyanavigationdrawer;
    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mDoctorrefrence;
    DatabaseReference mMentorrefrence;
    DatabaseReference mCounsellorrefrence;

    public void renderTestActivity(View view)
    {
        Intent intent = new Intent(AarogyaSarthiHomePage.this,TakeTest.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aarogya_sarthi_home_page);

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(AarogyaSarthiHomePage.this,R.color.yellowtheme));

        Objects.requireNonNull(getSupportActionBar()).hide();

        navigationView = findViewById(R.id.aarogyasarthinavigationview);
        navigationopenerimgview = findViewById(R.id.aarogyasarthinavigationopener);
        aarogyanavigationdrawer = findViewById(R.id.aarogyanavigationlayout);

        View headerview = navigationView.getHeaderView(0);
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        final FirebaseUser user = mauth.getCurrentUser();
        TextView useremail = headerview.findViewById(R.id.aarogyauseremail);
        TextView username = headerview.findViewById(R.id.aarogyausername);
        assert user != null;
        useremail.setText(user.getEmail());
        username.setText(user.getDisplayName());

        navigationopenerimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!aarogyanavigationdrawer.isDrawerOpen(GravityCompat.START))
                {
                    aarogyanavigationdrawer.openDrawer(GravityCompat.START);
                }
                else
                {
                    aarogyanavigationdrawer.closeDrawer(GravityCompat.END);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) AarogyaSarthiHomePage.this);





        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mDoctorrefrence = mdatabase.getReference("Doctors");
        mMentorrefrence = mdatabase.getReference("Mentors");
        mCounsellorrefrence = mdatabase.getReference("Counsellors");

        //checking if user is doctor or not;
        mDoctorrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(Objects.requireNonNull(user.getDisplayName())))
                {
                    Menu menu = navigationView.getMenu();
                    MenuItem menuitem = menu.findItem(R.id.check_appointments);
                    menuitem.setVisible(true);
                }
                else
                {
                    Menu menu = navigationView.getMenu();
                    MenuItem menuitem = menu.findItem(R.id.check_appointments);
                    menuitem.setVisible(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //checking if user is counsellor or not;
        mCounsellorrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(Objects.requireNonNull(user.getDisplayName())))
                {
                    Menu menu = navigationView.getMenu();
                    MenuItem menuitem = menu.findItem(R.id.check_appointments);
                    menuitem.setVisible(true);
                }
                else
                {
                    Menu menu = navigationView.getMenu();
                    MenuItem menuitem = menu.findItem(R.id.check_appointments);
                    menuitem.setVisible(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        models = new ArrayList<>();
        models.add(new Model(R.drawable.virusbackground,"What is Covid 19?","Covid 19 is a global pandemic as stated by WHO.Covid 19 is an infectious disease caused by the corona virus.","READ MORE"));
        models.add(new Model(R.drawable.volunteer_background,"Rise up and Volunteer to help the unfortunate","Get Location updates for donation drives. Do your bit in helping the poor.","VOLUNTEER"));
        models.add(new Model(R.mipmap.counsellor3,"Feeling stressed and Overwhelmed","Book an appointment with our Counsellors to relieve your tensions because sometimes speaking helps","view available counsellors"));
        models.add(new Model(R.drawable.covid_zones,"Covid 19 zones","Get to know if you're near a containment zone","explore zones"));
        models.add(new Model(R.drawable.covid_e_pass,"Apply for e-pass","Apply for e-pass to avoid any obstacles in your journey","register for epass"));
        models.add(new Model(R.drawable.medicalbackground,"Check out your nearest medical stores","During these tough times reach to your medical stores easily","GO TO MAPS"));
        models.add(new Model(R.drawable.statisticsbackground,"Covid 19 updates","Get latest info about the spread of corona virus","KNOW MORE"));
        models.add(new Model(R.drawable.covid_testing_labs,"Covid Test Labs","Find your nearest Covid testing labs, so you can get tested easily","CHECK OUT"));
        adapter = new Adapter(models,AarogyaSarthiHomePage.this);
        cardViewPager = findViewById(R.id.card_viewpager);
        cardViewPager.setAdapter(adapter);
        cardViewPager.setPadding(130,0,130,0);
        dotlayout = findViewById(R.id.dotslayout);

        addDotsIndicator(0);

        cardViewPager.addOnPageChangeListener(viewListener);


    }

    public void addDotsIndicator(int position){
        mDots = new TextView[models.size()];
        dotlayout.removeAllViews();

        for(int i=0; i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.greyishblack));



            dotlayout.addView(mDots[i]);
        }

        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.yellowtheme));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.profile:
                break;
            case R.id.set_reminder:
                Toast.makeText(this,"reminder clicked",Toast.LENGTH_SHORT).show();
                if(Build.VERSION.SDK_INT>= O)
                {
                    NotificationChannel channel = new NotificationChannel("notifyrudy","notifcationchannel",NotificationManager.IMPORTANCE_DEFAULT);
                    channel.setDescription("channel for rudy");
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }
                Intent notifyIntent = new Intent(AarogyaSarthiHomePage.this,ReminderReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AarogyaSarthiHomePage.this,200,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)AarogyaSarthiHomePage.this.getSystemService(Context.ALARM_SERVICE);
                long timeatbtnclick = System.currentTimeMillis();
                long teninmills = 1000*10;
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR,pendingIntent);
                break;
            case R.id.set_home_location:
                break;
            case R.id.check_appointments:
                Intent intent1 = new Intent(AarogyaSarthiHomePage.this,CheckAppointments.class);
                startActivity(intent1);
                break;
            case R.id.helpline_nos:
                Intent intent = new Intent(AarogyaSarthiHomePage.this, GetHelplineNos.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}