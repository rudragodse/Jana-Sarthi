package com.example.janasarthi.AarogyaSarthi;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.janasarthi.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerAdapter;

public class Adapter extends PagerAdapter {
    private List<Model> models;
    private LayoutInflater inflater;
    private Context context;
    private FusedLocationProviderClient fusedLocationProviderClient;
    Dialog homedialog;
    TextView closebtnforhome;
    MaterialCardView home_location_popup_layout;

    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.card, container, false);


        ImageView imageView;
        TextView title, desc;
        final MaterialButton databutton;

        imageView = view.findViewById(R.id.image);

        desc = view.findViewById(R.id.desc);
        databutton = view.findViewById(R.id.databutton);

        imageView.setImageResource(models.get(position).getImage());
        desc.setText(models.get(position).getDesc());
        databutton.setText(models.get(position).getButtontext());

        if (databutton.getText() == "READ MORE") {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Corona Tab Clicked", "read more about corona");
                    Intent intent = new Intent(context, CoronaTab.class);
                    context.startActivity(intent);

                }
            });
        } else if (databutton.getText() == "GO TO MAPS") {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=medical stores");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
                    context.startActivity(mapIntent);
                }
            });
        } else if (databutton.getText() == "KNOW MORE") {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Know more clicked", "you clicked know more");
                    Intent intent = new Intent(context, StatsPage.class);
                    context.startActivity(intent);
                }
            });
        } else if (databutton.getText() == "CHECK OUT") {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Check out clicked", "you clicked");
                    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        Log.i("Adapter", "permission failed");
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        Log.i("Got Location", location.getLatitude() + "" + location.getLongitude());
                                        getAddress(context, location.getLatitude(), location.getLongitude());
                                    }
                                });

                    } else {
                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        Log.i("Got Location", location.getLatitude() + "" + location.getLongitude());
                                        getAddress(context, location.getLatitude(), location.getLongitude());
                                    }
                                });
                    }


                }
            });
        } else if (databutton.getText() == "VOLUNTEER") {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        Log.i("Adapter", "permission failed");
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        Log.i("Got Location", location.getLatitude() + "" + location.getLongitude());
                                        getAddress(context, location.getLatitude(), location.getLongitude());
                                    }
                                });

                    } else {
                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        Log.i("Got Location", location.getLatitude() + "" + location.getLongitude());
                                        getCityname(context, location.getLatitude(), location.getLongitude());
                                    }
                                });
                    }
                }
            });
        } else if (databutton.getText() == "view available counsellors") {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ViewCounsellorActivity.class);
                    context.startActivity(intent);
                }
            });


        } else if (databutton.getText() == "explore zones") {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("zones", "clicked");
                    Intent intent = new Intent(context,NearbyZonesActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        else if(databutton.getText() == "register for epass")
        {
            databutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,E_Pass_Activity.class);
                    context.startActivity(intent);
                }
            });
        }


        container.addView(view,0);
        return view;
    }

    private static void getCityname(Context context,double Latitude,double Longitude) {
        try {

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(Latitude, Longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                String city = addresses.get(0).getLocality();
                Log.i("Got city", city);
//                Write Intent to next activity
                Intent intent = new Intent(context, VolunteerActivity.class);
                intent.putExtra("cityname", city);
                context.startActivity(intent);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void getAddress(Context context, double Latitude, double Longitude)
    {
        try{
            Geocoder geocoder= new Geocoder(context, Locale.getDefault());
            List<Address>addresses = geocoder.getFromLocation(Latitude,Longitude,1);
            if(addresses!=null && addresses.size()>0)
            {
                String city = addresses.get(0).getLocality();
                Log.i("Got city",city);
//                Write Intent to next activity
                Intent intent = new Intent(context,Labs.class);
                intent.putExtra("cityname",city);
                context.startActivity(intent);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
