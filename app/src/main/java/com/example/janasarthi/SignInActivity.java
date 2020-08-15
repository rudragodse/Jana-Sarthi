package com.example.janasarthi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    TextView signuptxt;
    TextInputEditText EmailSignInEdittext,PasswordSignInEdittext;
    MaterialButton loginbtn;
    FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Objects.requireNonNull(getSupportActionBar()).hide();

        signuptxt = findViewById(R.id.signuptxt);
        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,UserRoleActivity.class);
                startActivity(intent);
            }
        });

        EmailSignInEdittext = findViewById(R.id.EmailSignInEditText);
        PasswordSignInEdittext = findViewById(R.id.PasswordSignInEdittext);
        mauth = FirebaseAuth.getInstance();

        loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String signinemail = Objects.requireNonNull(EmailSignInEdittext.getText()).toString();
                String signinpassword = Objects.requireNonNull(PasswordSignInEdittext.getText()).toString();

                mauth.signInWithEmailAndPassword(signinemail,signinpassword)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(SignInActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignInActivity.this,JanaSarthiHomePageActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(SignInActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}