package com.example.janasarthi.AarogyaSarthi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.janasarthi.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ZonesRecyclerAdapter extends RecyclerView.Adapter<ZonesRecyclerAdapter.ViewHolder> {

    private List<String> zonesNames = new ArrayList<>();
    private Context context;

    public ZonesRecyclerAdapter(List<String> zonesNames, Context context) {
        this.zonesNames = zonesNames;
        this.context = context;
    }

    private static final String TAG = "ZonesRecyclerAdapter";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG,"Onbindviewholder called...");
        holder.zonenametxtview.setText(zonesNames.get(position));
    }

    @Override
    public int getItemCount() {
        return zonesNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView zonenametxtview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zonenametxtview = itemView.findViewById(R.id.zonename_txtview);
        }
    }

}
