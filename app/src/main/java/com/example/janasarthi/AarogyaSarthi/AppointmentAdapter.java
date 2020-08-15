package com.example.janasarthi.AarogyaSarthi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.janasarthi.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    Context context;
    List<AppointmentInfo>appointmentInfoList;

    public AppointmentAdapter(Context context, List<AppointmentInfo> appointmentInfoList) {
        this.context = context;
        this.appointmentInfoList = appointmentInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.appointment_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookingundername.setText(appointmentInfoList.get(position).getBookedunderthename());
        holder.timing_txt.setText(appointmentInfoList.get(position).getTiming());
    }

    @Override
    public int getItemCount() {
        return appointmentInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView bookingundername,timing_txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingundername = itemView.findViewById(R.id.bookingundername_txt1);
            timing_txt = itemView.findViewById(R.id.timing_txt);
        }
    }
}
