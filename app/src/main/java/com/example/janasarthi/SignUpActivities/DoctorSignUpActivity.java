package com.example.janasarthi.SignUpActivities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.example.janasarthi.SignInActivity;
import com.example.janasarthi.Users.Doctor;
import com.example.janasarthi.Users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class DoctorSignUpActivity extends AppCompatActivity {

    TextInputEditText DoctorNameEditText,RegistrationNoEditText,DateInputEditText,SpecializationInputEditText,
            StateMedicalCouncilEditText,DoctorEmailEditText,DoctorPasswordEditText;

    DatePickerDialog.OnDateSetListener dateSetListener;
    String date="";
    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence,Doctorrefrence;
    MaterialButton DoctorRegbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        DoctorNameEditText = findViewById(R.id.DoctorNameInputEditText);
        RegistrationNoEditText = findViewById(R.id.RegistrationNoInputEditText);
        DateInputEditText = findViewById(R.id.DateInputEditText);
        SpecializationInputEditText = findViewById(R.id.SpecializationInputEditText);
        StateMedicalCouncilEditText = findViewById(R.id.StateMedicalCouncilInputEditText);
        DoctorEmailEditText = findViewById(R.id.DoctorEmailInputEditText);
        DoctorPasswordEditText = findViewById(R.id.DoctorPasswordInputEditText);
        DoctorRegbtn = findViewById(R.id.DoctorRegbtn);

        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = FirebaseDatabase.getInstance().getReference("Users");
        Doctorrefrence = FirebaseDatabase.getInstance().getReference("Doctors");




        //Getting the date of registration;
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int propermonth= month+1;
                String fm=""+propermonth;
                String fd=""+dayOfMonth;
                if(month<10){
                    fm ="0"+propermonth;
                }
                if (dayOfMonth<10){
                    fd="0"+dayOfMonth;
                }
                date= ""+year+"-"+fm+"-"+fd;
                Log.i("startDate",date);
                DateInputEditText.setText(date);

            }
        };

        DateInputEditText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DoctorSignUpActivity.this,
                        dateSetListener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        //getting date ends here;





        DoctorRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("inside onclick","true");
                final String DoctorName = Objects.requireNonNull(DoctorNameEditText.getText()).toString();
                final String Regno = Objects.requireNonNull(RegistrationNoEditText.getText()).toString();
                final String dateofreg = Objects.requireNonNull(DateInputEditText.getText()).toString();
                final String specialization = Objects.requireNonNull(SpecializationInputEditText.getText()).toString();
                final String statemedicalcouncil = Objects.requireNonNull(StateMedicalCouncilEditText.getText()).toString();
                final String DoctorEmail = Objects.requireNonNull(DoctorEmailEditText.getText()).toString();
                final String DoctorPassword = Objects.requireNonNull(DoctorPasswordEditText.getText()).toString();

                mauth.createUserWithEmailAndPassword(DoctorEmail,DoctorPassword)
                        .addOnCompleteListener(DoctorSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(DoctorSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mauth.getCurrentUser();
                                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(DoctorName).build();
                                    assert user != null;
                                    user.updateProfile(profileChangeRequest);
                                    Doctor doctor = new Doctor(DoctorName,dateofreg,DoctorEmail,specialization,statemedicalcouncil,DoctorPassword,Regno);
                                    User doctoruser = new User(DoctorName,DoctorEmail,DoctorPassword,statemedicalcouncil,"default");
                                    String keyid = Doctorrefrence.push().getKey();
                                    if(keyid!=null)
                                    {
                                        Doctorrefrence.child(doctor.getName()).setValue(doctor);
                                        mrefrence.child(doctor.getName()).setValue(doctoruser);
                                        Intent intent = new Intent(DoctorSignUpActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Log.i("DoctorReg","Failed");
                                    }
                                }
                                else
                                {
                                    try {
                                        throw Objects.requireNonNull(task.getException());
                                    } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                        Toast.makeText(DoctorSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                        Toast.makeText(DoctorSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthUserCollisionException usercollision){
                                        Toast.makeText(DoctorSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
            }
        });



    }
}