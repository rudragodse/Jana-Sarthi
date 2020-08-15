package com.example.janasarthi.SignUpActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.janasarthi.R;
import com.example.janasarthi.SignInActivity;
import com.example.janasarthi.Users.Organization;
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

public class OrganizationSignUpActivity extends AppCompatActivity {

    TextInputEditText OrgNameEditTxt,OrgServiceEdittxt,OrgContactEdittxt,OrgEmailEdittxt,OrgPasswordEdittxt,OrgStateEdittxt;
    MaterialButton OrgRegbtn;
    FirebaseAuth mauth;
    FirebaseDatabase mdatabase;
    DatabaseReference mrefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        OrgNameEditTxt = findViewById(R.id.OrgNameEditText);
        OrgServiceEdittxt = findViewById(R.id.OrgServiceEdittxt);
        OrgContactEdittxt = findViewById(R.id.OrgContactEdittxt);
        OrgEmailEdittxt = findViewById(R.id.OrgEmailEdittxt);
        OrgPasswordEdittxt = findViewById(R.id.OrgPasswordEdittxt);
        OrgStateEdittxt = findViewById(R.id.OrgStateEdittxt);
        OrgRegbtn = findViewById(R.id.OrgRegbtn);

        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mrefrence = mdatabase.getReference("Non profit organizations");

        OrgRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String orgname = Objects.requireNonNull(OrgNameEditTxt.getText()).toString();
                final String orgservice = Objects.requireNonNull(OrgServiceEdittxt.getText()).toString();
                final String orgcontact = Objects.requireNonNull(OrgContactEdittxt.getText()).toString();
                final String orgemail = Objects.requireNonNull(OrgEmailEdittxt.getText()).toString();
                final String orgpassword = Objects.requireNonNull(OrgPasswordEdittxt.getText()).toString();
                final String orgstate = Objects.requireNonNull(OrgStateEdittxt.getText()).toString();

                mauth.createUserWithEmailAndPassword(orgemail,orgpassword)
                        .addOnCompleteListener(OrganizationSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(OrganizationSignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mauth.getCurrentUser();
                                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(orgname).build();
                                    assert user != null;
                                    user.updateProfile(profileChangeRequest);

                                    Organization organization = new Organization(orgname,orgservice,orgcontact,orgstate,orgpassword,orgemail);
                                    String keyid = mrefrence.push().getKey();
                                    if(keyid!=null)
                                    {
                                        mrefrence.child(organization.getNameoforganization()).setValue(organization);
                                        Intent intent = new Intent(OrganizationSignUpActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                    }
                                }
                                else
                                {
                                    try {
                                        throw Objects.requireNonNull(task.getException());
                                    } catch (FirebaseAuthWeakPasswordException weakpassword) {
                                        Toast.makeText(OrganizationSignUpActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthInvalidCredentialsException invalidcredential){
                                        Toast.makeText(OrganizationSignUpActivity.this, "Invalid Credentials entered", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (FirebaseAuthUserCollisionException usercollision){
                                        Toast.makeText(OrganizationSignUpActivity.this, "User with entered credentials already exists", Toast.LENGTH_SHORT).show();
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