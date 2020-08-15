package com.example.janasarthi.SignUpActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.example.janasarthi.SignInActivity;
import com.example.janasarthi.Users.Counsellor;
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

import java.util.Objects;

public class CounsellorSignUpActivity extends AppCompatActivity {

    TextInputEditText CounsellornameEdittxt,CounsellorspecializationEdittxt,CounsellorqualificationEdittxt,Counsellorstateedittext,Counsellorcontactedittext,CounselloremailEdittxt,CounsellorpasswordEdittxt;
    MaterialButton CounsellorRegisterbtn;
    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence,counselloruserrefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        CounsellornameEdittxt = findViewById(R.id.CounsellorNameEditText);
        CounsellorspecializationEdittxt = findViewById(R.id.CounsellorSpecializationEdittxt);
        CounsellorqualificationEdittxt = findViewById(R.id.CounsellorQualificationEdittxt);
        CounselloremailEdittxt = findViewById(R.id.CounsellorEmailEdittxt);
        CounsellorpasswordEdittxt = findViewById(R.id.CounsellorPasswordEdittxt);
        Counsellorstateedittext = findViewById(R.id.CounsellorStateedittext);
        CounsellorRegisterbtn = findViewById(R.id.CousellorRegbtn);
        Counsellorcontactedittext = findViewById(R.id.counsellor_contact);

        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Counsellors");
        counselloruserrefrence = mdatabase.getReference("Users");

        CounsellorRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Counsellorname = Objects.requireNonNull(CounsellornameEdittxt.getText()).toString();
                final String Counsellorspecialization = Objects.requireNonNull(CounsellorspecializationEdittxt.getText()).toString();
                final String Counsellorqualification = Objects.requireNonNull(CounsellorqualificationEdittxt.getText()).toString();
                final String Counselloremail = Objects.requireNonNull(CounselloremailEdittxt.getText()).toString();
                final String Counsellorpassword = Objects.requireNonNull(CounsellorpasswordEdittxt.getText()).toString();
                final String Counsellorstate = Objects.requireNonNull(Counsellorstateedittext.getText()).toString();
                final String Counsellorcontact = Objects.requireNonNull(Counsellorcontactedittext.getText()).toString();

                mauth.createUserWithEmailAndPassword(Counselloremail,Counsellorpassword)
                        .addOnCompleteListener(CounsellorSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(CounsellorSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mauth.getCurrentUser();
                                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(Counsellorname).build();
                                    assert user != null;
                                    user.updateProfile(profileChangeRequest);

                                    Counsellor counsellor = new Counsellor(Counsellorname,Counselloremail,Counsellorspecialization,Counsellorqualification,Counsellorpassword,Counsellorstate,Counsellorcontact);
                                    User counselloruser = new User(Counsellorname,Counselloremail,Counsellorpassword,Counsellorstate,"default");
                                    String keyid = mrefrence.push().getKey();
                                    if(keyid!=null)
                                    {
                                        mrefrence.child(counsellor.getName()).setValue(counsellor);
                                        counselloruserrefrence.child(counsellor.getName()).setValue(counselloruser);
                                        Intent intent = new Intent(CounsellorSignUpActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Log.i("CounsellorReg","Failed");
                                    }
                                }
                                else
                                {
                                    try {
                                        throw Objects.requireNonNull(task.getException());
                                    } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                        Toast.makeText(CounsellorSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                        Toast.makeText(CounsellorSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthUserCollisionException usercollision){
                                        Toast.makeText(CounsellorSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
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