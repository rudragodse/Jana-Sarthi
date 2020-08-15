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
import com.example.janasarthi.Users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
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

public class UserSignUpActivity extends AppCompatActivity {

    TextInputEditText NameInputEditText,EmailInputEditText,PasswordInputEditText,StateEditText;
    private FirebaseAuth mauth;
    private FirebaseDatabase mdatabase;
    DatabaseReference mreference;
    MaterialButton regbtn;

    Chip healthworkerchip,essentialworkerchip,volunteerchip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        Intent intent = getIntent();
        String role = intent.getStringExtra("role");
        assert role != null;
        Objects.requireNonNull(getSupportActionBar()).hide();
        NameInputEditText = findViewById(R.id.NameInputEditText);
        EmailInputEditText = findViewById(R.id.EmailInputEditText);
        PasswordInputEditText = findViewById(R.id.PasswordInputEditText);
        StateEditText = findViewById(R.id.statedittext);
        regbtn = findViewById(R.id.registerbtn);
        healthworkerchip = findViewById(R.id.healthworkerchip);
        essentialworkerchip = findViewById(R.id.essentialworkerchip);
        volunteerchip = findViewById(R.id.volunteerchip);


        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mreference = mdatabase.getReference("Users");

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("UserSignUpActivity","RegButn Clicked");
                final String email = Objects.requireNonNull(EmailInputEditText.getText()).toString();
                final String password = Objects.requireNonNull(PasswordInputEditText.getText()).toString();
                final String Name = Objects.requireNonNull(NameInputEditText.getText()).toString();
                final String state = Objects.requireNonNull(StateEditText.getText()).toString();
                Log.i("UserData",email);

                //user creation code;
                if (healthworkerchip.isChecked())
                {
                    mauth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(UserSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(UserSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mauth.getCurrentUser();
                                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
                                        assert user != null;
                                        user.updateProfile(profileChangeRequest);
                                        User myuser = new User(Name,email,password,state,"Health worker");
                                        String keyid =mreference.push().getKey();
                                        if(keyid!=null)
                                        {
                                            mreference.child(Objects.requireNonNull(myuser.getName())).setValue(myuser);
                                            Intent intent = new Intent(UserSignUpActivity.this, SignInActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Log.i("Reg","Failed");
                                        }
                                    }
                                    else
                                    {
                                        try {
                                            throw Objects.requireNonNull(task.getException());
                                        } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                            Toast.makeText(UserSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                            Toast.makeText(UserSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthUserCollisionException usercollision){
                                            Toast.makeText(UserSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                }
                else if(essentialworkerchip.isChecked())
                {
                    mauth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(UserSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(UserSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mauth.getCurrentUser();
                                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
                                        assert user != null;
                                        user.updateProfile(profileChangeRequest);
                                        User myuser = new User(Name,email,password,state,"Essential worker");
                                        String keyid =mreference.push().getKey();
                                        if(keyid!=null)
                                        {
                                            mreference.child(Objects.requireNonNull(myuser.getName())).setValue(myuser);
                                            Intent intent = new Intent(UserSignUpActivity.this,SignInActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Log.i("Reg","Failed");
                                        }
                                    }
                                    else
                                    {
                                        try {
                                            throw Objects.requireNonNull(task.getException());
                                        } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                            Toast.makeText(UserSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                            Toast.makeText(UserSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthUserCollisionException usercollision){
                                            Toast.makeText(UserSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                }
                else if(volunteerchip.isChecked())
                {
                    mauth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(UserSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(UserSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mauth.getCurrentUser();
                                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
                                        assert user != null;
                                        user.updateProfile(profileChangeRequest);
                                        User myuser = new User(Name,email,password,state,"Volunteer");
                                        String keyid =mreference.push().getKey();
                                        if(keyid!=null)
                                        {
                                            mreference.child(Objects.requireNonNull(myuser.getName())).setValue(myuser);
                                            Intent intent = new Intent(UserSignUpActivity.this,SignInActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Log.i("Reg","Failed");
                                        }
                                    }
                                    else
                                    {
                                        try {
                                            throw Objects.requireNonNull(task.getException());
                                        } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                            Toast.makeText(UserSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                            Toast.makeText(UserSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthUserCollisionException usercollision){
                                            Toast.makeText(UserSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                }
                else if(healthworkerchip.isChecked() && essentialworkerchip.isChecked() && volunteerchip.isChecked())
                {
                    Toast.makeText(UserSignUpActivity.this, "Please select one of the following", Toast.LENGTH_SHORT).show();
                }
                else if(healthworkerchip.isChecked() && essentialworkerchip.isChecked()
                || healthworkerchip.isChecked() && volunteerchip.isChecked() || essentialworkerchip.isChecked()&&volunteerchip.isChecked())
                {
                    Toast.makeText(UserSignUpActivity.this, "Please select one of the following", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mauth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(UserSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(UserSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mauth.getCurrentUser();
                                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
                                        assert user != null;
                                        user.updateProfile(profileChangeRequest);
                                        User myuser = new User(Name,email,password,state,"default");
                                        String keyid =mreference.push().getKey();
                                        if(keyid!=null)
                                        {
                                            mreference.child(Objects.requireNonNull(myuser.getName())).setValue(myuser);
                                            Intent intent = new Intent(UserSignUpActivity.this,SignInActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Log.i("Reg","Failed");
                                        }
                                    }
                                    else
                                    {
                                        try {
                                            throw Objects.requireNonNull(task.getException());
                                        } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                            Toast.makeText(UserSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                            Toast.makeText(UserSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (FirebaseAuthUserCollisionException usercollision){
                                            Toast.makeText(UserSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                }


            }

        });

    }
}