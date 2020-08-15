package com.example.janasarthi.KaushalSarthi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.janasarthi.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoursesRecyclerAdapter extends RecyclerView.Adapter<CoursesRecyclerAdapter.ViewHolder> {

    List<CourseInfo> courseInfoList;
    Context context;

    public CoursesRecyclerAdapter(List<CourseInfo> courseInfoList, Context context) {
        this.courseInfoList = courseInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sector_courses_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String sectorname = courseInfoList.get(position).getSectorname();
        switch (sectorname)
        {
            case "Aerospace and Aviation":
                holder.theme_img.setImageResource(R.drawable.aerospace_background);
                break;
            case "Agriculture":
                holder.theme_img.setImageResource(R.drawable.agriculture_background);
                break;
            case "Apparel":
                holder.theme_img.setImageResource(R.drawable.apparel_background);
                break;
            case "Automotive":
                holder.theme_img.setImageResource(R.drawable.automotive_mechanic);
                break;
            case "BFSI(Banking, Finance Service and Insurance)":
                holder.theme_img.setImageResource(R.drawable.banking_finance1);
                break;
            case "Beauty and Wellness":
                holder.theme_img.setImageResource(R.drawable.beauty_and_wellness_background);
                break;
            case "Capital Goods and Manufacturing":
                holder.theme_img.setImageResource(R.drawable.manufacturing_background);
                break;
            case "Construction":
                holder.theme_img.setImageResource(R.drawable.construction_background);
                break;
            case "Chemicals and Petrochemicals":
                holder.theme_img.setImageResource(R.drawable.petrochemicals);
                break;
            case "Education,Training and Research":
                holder.theme_img.setImageResource(R.drawable.mentor_1);
                break;
            case "Power":
                holder.theme_img.setImageResource(R.drawable.power_1);
                break;
            case "Electronics and HW":
                holder.theme_img.setImageResource(R.drawable.electronics_and_hw);
                break;
            case "IT and ITES":
                holder.theme_img.setImageResource(R.drawable.computer);
                break;
            case "Environmental Science":
                holder.theme_img.setImageResource(R.drawable.enviromental_science);
                break;
            case "Food Industry":
                holder.theme_img.setImageResource(R.drawable.food_industry_1);
                break;
            case "Tourism and Hospitality":
                holder.theme_img.setImageResource(R.drawable.tourism_hospitality);
                break;
            case "Office Administration and Facility Management":
                holder.theme_img.setImageResource(R.drawable.office_administration);
                break;
            case "Handicrafts and Carpets":
                holder.theme_img.setImageResource(R.drawable.handicrafts);
                break;
            case "Healthcare":
                holder.theme_img.setImageResource(R.drawable.laboratory_1);
                break;
            case "Leather":
                holder.theme_img.setImageResource(R.drawable.leather_1);
                break;
            case "Media and Entertainment":
                holder.theme_img.setImageResource(R.drawable.media);
                break;
            case "Mining":
                holder.theme_img.setImageResource(R.drawable.mining);
                break;
            case "Plumbing":
                holder.theme_img.setImageResource(R.drawable.plumbing);
                break;
            case "Safety and Security":
                holder.theme_img.setImageResource(R.drawable.security_1);
            case "Private Security":
                holder.theme_img.setImageResource(R.drawable.fireman_1);
                break;
            case "Rubber Industry":
                holder.theme_img.setImageResource(R.drawable.rubber_1);
                break;
            case "Textile and Handloom":
                holder.theme_img.setImageResource(R.drawable.textile_1);
                break;





        }
        holder.coursename.setText(courseInfoList.get(position).getCoursename());
        holder.coursecategory.setText(courseInfoList.get(position).getCategory());
        holder.duration.setText(courseInfoList.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return courseInfoList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder
    {
        ImageView theme_img;
        TextView coursename,coursecategory,duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            theme_img = itemView.findViewById(R.id.theme_img);
            coursename = itemView.findViewById(R.id.course_title);
            coursecategory = itemView.findViewById(R.id.course_category);
            duration = itemView.findViewById(R.id.course_duration);
        }
    }
}
