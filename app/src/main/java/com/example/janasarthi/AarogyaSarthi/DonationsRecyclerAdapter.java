package com.example.janasarthi.AarogyaSarthi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class DonationsRecyclerAdapter extends RecyclerView.Adapter<DonationsRecyclerAdapter.ViewHolder> {

    ArrayList<DonationInfo>donationlist;
    Context context;

    DonationsRecyclerAdapter(ArrayList<DonationInfo>donationlist, Context context)
    {
        this.donationlist = donationlist;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.free_food_donation_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.d("DonationRecyclerAdapter","onBindViewHoldercalled..");
        holder.freefoodcategory.setText(donationlist.get(position).getCategory());
        holder.nameoffoodorg.setText(donationlist.get(position).getTitleofOrg());
        holder.freefooddesc.setText(donationlist.get(position).getDesc());
        holder.contactnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("tel:"+donationlist.get(position).getContact());
                Intent i = new Intent(Intent.ACTION_DIAL,uri);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return donationlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameoffoodorg,freefoodcategory,freefooddesc;
        ConstraintLayout freefoodlayout;
        MaterialButton contactnowbtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameoffoodorg = itemView.findViewById(R.id.nameoffoodorg);
            freefoodcategory = itemView.findViewById(R.id.freefoodcategory);
            freefooddesc= itemView.findViewById(R.id.freefooddesc);
            freefoodlayout = itemView.findViewById(R.id.freefoodlayout);
            contactnowbtn =  itemView.findViewById(R.id.contactnowbtn);


        }
    }
}
