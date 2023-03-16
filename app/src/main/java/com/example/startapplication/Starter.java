package com.example.startapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Starter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        Intent nextact = new Intent(Starter.this,MainActivity.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(nextact);
                finish();
            }
        },4000);

    }
}
//    TextView t = findViewById(R.id.starticon);
//    Animation move = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//        t.startAnimation(move);