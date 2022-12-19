package com.example.urecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.urecipe.signup.SignUpActivity;

import java.util.Timer;
import java.util.TimerTask;


public class OpeningActivity extends AppCompatActivity {

    //Create a timer for an opening page
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_activity);
        getSupportActionBar().hide();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //After 3 seconds open an OpenActivity2
                Intent intent = new Intent(OpeningActivity.this, OpeningActivity2.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}