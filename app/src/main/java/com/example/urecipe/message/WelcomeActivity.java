package com.example.urecipe.message;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urecipe.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_welcome_dialog);
        getSupportActionBar().setTitle("Welcome Message");
    }
}
