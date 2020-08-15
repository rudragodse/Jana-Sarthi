package com.example.janasarthi.AarogyaSarthi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<LabsFormat> labdetails;
    private Context context;

    RecyclerViewAdapter(ArrayList<LabsFormat> labdetails, Context context) {
        this.labdetails = labdetails;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_labs_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHoldercalled..");
        holder.categorytxtview.setText(labdetails.get(position).getCategory());
        holder.labname.setText(labdetails.get(position).getNameoforganization());
        holder.labphoneno.setText(labdetails.get(position).getPhonenumber());
        holder.testlablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked on"+labdetails.get(position).getNameoforganization());
                Toast.makeText(context,labdetails.get(position).getNameoforganization(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.callnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("tel:"+holder.labphoneno.getText().toString());
                Intent i = new Intent(Intent.ACTION_DIAL,uri);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+labdetails.size());
        return labdetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView labname,labphoneno,categorytxtview;
        LinearLayout testlablayout;
        MaterialButton callnowbtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            categorytxtview= itemView.findViewById(R.id.categorytxtview);
            labname = itemView.findViewById(R.id.labname);
            labphoneno = itemView.findViewById(R.id.labphoneno);
            testlablayout = itemView.findViewById(R.id.testlablayout);
            callnowbtn =  itemView.findViewById(R.id.callnowbtn);

        }
    }

}
