package com.example.startapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);

        Intent suserdetail = getIntent();
        String suseremail =  suserdetail.getStringExtra("suseremail");
        String suserpassword =  suserdetail.getStringExtra("suserpassword");
        //int suserphone = suserdetail.getIntExtra("suserphone",0);

        TextView lemail = (TextView)findViewById(R.id.ltextemail);
        TextView lpassword = (TextView)findViewById(R.id.ltextpassword);
        Button btnlogin = findViewById(R.id.loginbutton);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(suseremail.equals(lemail.getText().toString()) && suserpassword.equals(lpassword.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Sucessfull Login", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Failed Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent signuppage = new Intent(this,SignupPage.class);
        Button btnsignup = findViewById(R.id.signupbutton);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signuppage);
            }
        });
    }
}