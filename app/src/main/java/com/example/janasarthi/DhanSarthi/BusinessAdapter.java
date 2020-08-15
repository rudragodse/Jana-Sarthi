package com.example.janasarthi.DhanSarthi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    public Context context;
    public List<SubServicesInfo>businessInfoList;

    public BusinessAdapter(Context context, List<SubServicesInfo> businessInfoList) {
        this.context = context;
        this.businessInfoList = businessInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.business_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        if(businessInfoList.get(position).getNameofservice().equalsIgnoreCase("Legal Compliances"))
        {
            holder.item_theme.setImageResource(R.drawable.legal);
        }
        else if(businessInfoList.get(position).getNameofservice().equalsIgnoreCase("Business plan and loan proposal"))
        {
            holder.item_theme.setImageResource(R.drawable.loan);
        }
        else if(businessInfoList.get(position).getNameofservice().equalsIgnoreCase("Business Process Enhancement"))
        {
            holder.item_theme.setImageResource(R.drawable.assessment);
        }
        holder.nameofsubservicetxt.setText(businessInfoList.get(position).getSub_service_name());
        holder.request_service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(businessInfoList.get(position).getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return businessInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView item_theme;
        TextView nameofsubservicetxt;
        MaterialButton request_service_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_theme = itemView.findViewById(R.id.item_theme);
            nameofsubservicetxt = itemView.findViewById(R.id.nameofsubservicetxt);
            request_service_btn = itemView.findViewById(R.id.requestservicebtn);
        }
    }


}
