package com.example.startapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ProgressBar pb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.progressBar);
        setContentView(R.layout.activity_signup_page);
        Intent loginpagefromsignup = new Intent(this,LoginPage.class);

        TextView semail = (TextView)findViewById(R.id.stextemail);
        EditText sphone = (EditText)    findViewById(R.id.stextphone);
        TextView spassword = (TextView)findViewById(R.id.stextpassword);


        Button signup = findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(SignupPage.this);
                String email = semail.getText().toString().trim();
                String password = spassword.getText().toString().trim();
                String phone = sphone.getText().toString().trim();
                if(email.isEmpty())
                {
                    semail.setError("Email is required");
                    semail.requestFocus();
                    return;
                }
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    semail.setError("Please provide valid email! ");
                    semail.requestFocus();
                    return;
                }
                if(spassword.getText().toString().isEmpty())
                {
                    spassword.setError("Please provide password! ");
                    spassword.requestFocus();
                    return;
                }
                if(sphone.getText().toString().isEmpty())
                {
                    semail.setError("Phone number is required");
                    semail.requestFocus();
                    return;
                }
                if(sphone.getText().toString().length()!=10)
                {
                    sphone.setError("Please provide valid phone number! ");
                    sphone.requestFocus();
                     return;
                }
                else
                {
                    pb.setVisibility(view.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email,password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                             if(task.isSuccessful())
                                             {
                                                 user u = new user (email,password,phone);
                                                 FirebaseDatabase.getInstance().getReference("users")
                                                         .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                         .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                             @Override
                                                             public void onComplete(@NonNull Task<Void> task) {
                                                                 if(task.isSuccessful())
                                                                 {
                                                                     Toast.makeText(SignupPage.this, "Register Sucessfully", Toast.LENGTH_SHORT).show();
                                                                     pb.setVisibility(view.VISIBLE);
                                                                 }
                                                                 else
                                                                 {
                                                                     Toast.makeText(SignupPage.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                                                                     pb.setVisibility(view.GONE);
                                                                 }
                                                             }
                                                         });
                                             }
                                        }
                                    });
                    dialog.setContentView(R.layout.sucessview);
                    dialog.show();
                    Button dok  =(Button)dialog.findViewById(R.id.dok);
                    loginpagefromsignup.putExtra("suseremail",semail.getText().toString());
                    //loginpagefromsignup.putExtra("suserphone",sphone.getText().toString());
                    loginpagefromsignup.putExtra("suserpassword",spassword.getText().toString());
                    dok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            startActivity(loginpagefromsignup);
                        }
                    });
                }
            }
        });

        Button login = findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginpagefromsignup);
            }
        });
    }
}