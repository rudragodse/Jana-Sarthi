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

public class EssentialAdapter extends RecyclerView.Adapter<EssentialAdapter.ViewHolder> {

    public Context context;
    public List<CovidEssentialProduct>essentialProductList;

    public EssentialAdapter(Context context, List<CovidEssentialProduct> essentialProductList) {
        this.context = context;
        this.essentialProductList = essentialProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.essential_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.essential_imageview.setImageResource(essentialProductList.get(position).getImageforproduct());
        holder.nameofessentialtextview.setText(essentialProductList.get(position).getNameofproduct());
        holder.register_as_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(essentialProductList.get(position).getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return essentialProductList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView essential_imageview;
        TextView nameofessentialtextview;
        MaterialButton register_as_seller;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            essential_imageview = itemView.findViewById(R.id.essential_imageview);
            nameofessentialtextview = itemView.findViewById(R.id.nameofessential_product);
            register_as_seller = itemView.findViewById(R.id.register_as_seller_btn);
        }
    }
}
