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
import com.example.janasarthi.Users.Mentor;
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

public class MentorSignUpActivity extends AppCompatActivity {

    TextInputEditText MentornameEdittxt,MentoremailEdittxt,MentorpasswordEdittxt,Mentorstateedittext,MentorsectorEdittxt,MentorcourseEdittxt;
    MaterialButton MentorRegisterbtn;
    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence,mentoruserrefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        MentornameEdittxt = findViewById(R.id.MentorNameEditText);
        MentoremailEdittxt = findViewById(R.id.MentorEmailEditText);
        MentorpasswordEdittxt = findViewById(R.id.MentorPasswordEditText);
        Mentorstateedittext = findViewById(R.id.Mentorstateedittext);
        MentorsectorEdittxt = findViewById(R.id.MentorSectorEditText);
        MentorcourseEdittxt = findViewById(R.id.MentorCourseEditText);
        MentorRegisterbtn = findViewById(R.id.MentorRegisterbtn);

        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Mentors");
        mentoruserrefrence = mdatabase.getReference("Users");

        MentorRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mentorname = Objects.requireNonNull(MentornameEdittxt.getText()).toString();
                final String mentoremail = Objects.requireNonNull(MentoremailEdittxt.getText()).toString();
                final String mentorpassword = Objects.requireNonNull(MentorpasswordEdittxt.getText()).toString();
                final String mentorsector = Objects.requireNonNull(MentorsectorEdittxt.getText()).toString();
                final String mentorcourse = Objects.requireNonNull(MentorcourseEdittxt.getText()).toString();
                final String mentorstate = Objects.requireNonNull(Mentorstateedittext.getText()).toString();

                mauth.createUserWithEmailAndPassword(mentoremail,mentorpassword)
                        .addOnCompleteListener(MentorSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(MentorSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mauth.getCurrentUser();
                                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(mentorname).build();
                                    assert user != null;
                                    user.updateProfile(profileChangeRequest);

                                    Mentor mentor = new Mentor(mentorname,mentoremail,mentorpassword,mentorstate,mentorsector,mentorcourse);
                                    User mentoruser = new User(mentorname,mentoremail,mentorpassword,mentorstate,"default");
                                    String keyuid = mrefrence.push().getKey();
                                    if(keyuid!= null)
                                    {
                                        mrefrence.child(mentor.getName()).setValue(mentor);
                                        mentoruserrefrence.child(mentor.getName()).setValue(mentoruser);
                                        Intent intent = new Intent(MentorSignUpActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Log.i("keyid","null");
                                    }
                                }
                                else
                                {
                                    try {
                                        throw Objects.requireNonNull(task.getException());
                                    } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                        Toast.makeText(MentorSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                        Toast.makeText(MentorSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthUserCollisionException usercollision){
                                        Toast.makeText(MentorSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
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