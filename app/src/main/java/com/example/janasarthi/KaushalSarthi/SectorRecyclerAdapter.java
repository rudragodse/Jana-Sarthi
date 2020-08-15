package com.example.janasarthi.KaushalSarthi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.janasarthi.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SectorRecyclerAdapter extends RecyclerView.Adapter<SectorRecyclerAdapter.ViewHolder> {

    List<SectorInfo> sectorInfoList= new ArrayList<>();
    Context context;

    public SectorRecyclerAdapter(List<SectorInfo> sectorInfoList, Context context) {
        this.sectorInfoList = sectorInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sector_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.sectorimg.setImageResource(sectorInfoList.get(position).getThumbnail());
        holder.sectorname.setText(sectorInfoList.get(position).getSectoname());
        holder.sectorcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,KaushalSarthiSubjects.class);
                intent.putExtra("nameofsector",sectorInfoList.get(position).getSectoname());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sectorInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView sectorimg;
        TextView sectorname;
        MaterialCardView sectorcard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sectorimg = itemView.findViewById(R.id.sector_img);
            sectorname = itemView.findViewById(R.id.sector_name_txt);
            sectorcard = itemView.findViewById(R.id.sector_card);
        }
    }

}
