package com.example.janasarthi.AarogyaSarthi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class  CounsellorRecyclerAdapter extends RecyclerView.Adapter<CounsellorRecyclerAdapter.ViewHolder> {


    List<CounsellorData>counsellorobjs = new ArrayList<>();
    Context context;
    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence;
    public static JSONArray appointmentsarray=new JSONArray();

    public CounsellorRecyclerAdapter(List<CounsellorData> counsellorobjs, Context context) {
        this.counsellorobjs = counsellorobjs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.counsellor_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.doctornametxt.setText(counsellorobjs.get(position).getName());
        holder.qualificationtxt.setText(counsellorobjs.get(position).getQualification());
        holder.specializationtxt.setText("Dr."+" "+counsellorobjs.get(position).getName()+" "+"specializes in"+" "+counsellorobjs.get(position).getSpecialization());


        holder.bookappointmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cardflipview.setFlipDuration(1000);
                holder.cardflipview.flipTheView();


            }
        });

        holder.goback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cardflipview.setFlipDuration(1000);
                holder.cardflipview.flipTheView();
            }
        });
        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Counsellor Appointments");
        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-MM-yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                holder.select_date_edit_txt.setText(sdf.format(calendar.getTime()));

            }

        };

        holder.select_date_edit_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        holder.time_slot_chip_group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = group.findViewById(checkedId);
                if(chip.getText().equals("10-11am"))
                {
                    Log.i("10-11","true");
                }
                if(chip.getText().equals("11-12am"))
                {
                    Log.i("11-12","true");
                }
                if(chip.getText().equals("12-01pm"))
                {
                    Log.i("12-01","true");
                }
                if(chip.getText().equals("3-4 pm"))
                {
                    Log.i("3-4","true");
                }
                if(chip.getText().equals("4-5 pm"))
                {
                    Log.i("4-5","true");
                }
                if(chip.getText().equals("5-6 pm"))
                {
                    Log.i("5-6","true");


                }

            }
        });


        holder.confirmappointmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("confirm_btn",holder.doctornametxt.getText().toString());//data has been received;
                final String user = Objects.requireNonNull(mauth.getCurrentUser()).getDisplayName();

                int checkedChipId=holder.time_slot_chip_group.getCheckedChipId();
                Chip chip = holder.time_slot_chip_group.findViewById(checkedChipId);
                Log.i("checkedchip",chip.getText().toString());
                final String chiptext = chip.getText().toString();
                final AppointmentData appointmentData = new AppointmentData(user,
                        holder.doctornametxt.getText().toString(),
                        Objects.requireNonNull(holder.select_date_edit_txt.getText()).toString(),
                        chip.getText().toString());

                //newcode
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(holder.doctornametxt.getText().toString(),appointmentData);
                    appointmentsarray.put(jsonObject);
                    Log.i("appointmentarray",appointmentsarray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //new code;

                String keyid = mrefrence.push().getKey();
                if(keyid!=null)
                {
                    assert user != null;
                    mrefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(appointmentData.getDoctorname() ))
                            {
                                //stupid code;
                                DatabaseReference dateref = mdatabase.getReference("Counsellor Appointments").child(appointmentData.getDoctorname());
                                //creating another refrence for time slot;
                                dateref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.hasChild(appointmentData.getTimeslot()))
                                        {
                                            DatabaseReference timeslotref = mdatabase.getReference("Counsellor Appointments").child(appointmentData.getDoctorname()).child(appointmentData.getDateofappointment()).child(appointmentData.getTimeslot());
                                            timeslotref.child(appointmentData.getCurrentuser()).setValue(appointmentData);
                                            Toast.makeText(context, "Appointment booked", Toast.LENGTH_SHORT).show();
                                        }
                                        
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                //ends here;
                                dateref.child(appointmentData.getDateofappointment()).child(appointmentData.getTimeslot()).child(appointmentData.getCurrentuser()).setValue(appointmentData);
                                Toast.makeText(context, "Appointment booked", Toast.LENGTH_SHORT).show();
                                //stupid code ends;
//                                mrefrence.child(appointmentData.getTimeslot()).setValue(appointmentData);
//                                Toast.makeText(context, "Appointment Booked", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                mrefrence.child(appointmentData.getDoctorname()).child(appointmentData.getDateofappointment()).child(appointmentData.getTimeslot()).child(appointmentData.getCurrentuser()).setValue(appointmentData);
                                Toast.makeText(context, "Appointment Booked", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }






            }
        });

    }




    @Override
    public int getItemCount() {
        return counsellorobjs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ConstraintLayout back_card_layout,front_card_layout;
        EasyFlipView cardflipview;
        //front side card layout items;
        TextView doctornametxt,qualificationtxt,specializationtxt;
        MaterialButton bookappointmentbtn;

        //back side card layout items;
        MaterialButton confirmappointmentbtn,goback_btn;
        TextInputEditText select_date_edit_txt;
        ChipGroup time_slot_chip_group;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            back_card_layout = itemView.findViewById(R.id.backside_item);
            confirmappointmentbtn = itemView.findViewById(R.id.confirm_appointment_btn);
            goback_btn = itemView.findViewById(R.id.goback_btn);
            select_date_edit_txt = itemView.findViewById(R.id.select_date_slot_edit_txt);
            time_slot_chip_group = itemView.findViewById(R.id.time_slot_chip_group);

            front_card_layout = itemView.findViewById(R.id.frontside_item);
            doctornametxt = itemView.findViewById(R.id.doctornametxt);
            qualificationtxt = itemView.findViewById(R.id.qualificationtxt);
            specializationtxt = itemView.findViewById(R.id.specializationtxt);
            bookappointmentbtn = itemView.findViewById(R.id.book_appointment_btn);

            cardflipview = itemView.findViewById(R.id.card_flip_view);
        }
    }
}
