package com.example.janasarthi.AarogyaSarthi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EPassAdapter extends RecyclerView.Adapter<EPassAdapter.ViewHolder> {

    List<EPassInfo>passlist;
    Context context;

    public EPassAdapter(List<EPassInfo> passlist, Context context) {
        this.passlist = passlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.e_pass_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.servicenametxt.setText(passlist.get(position).getServicename());
        holder.apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!passlist.get(position).getLink().equals("NA"))
                {
                    Uri uri = Uri.parse(passlist.get(position).getLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return passlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView servicenametxt;
        MaterialButton apply_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            servicenametxt = itemView.findViewById(R.id.servicenametxt);
            apply_btn = itemView.findViewById(R.id.apply_btn);
        }
    }
}
