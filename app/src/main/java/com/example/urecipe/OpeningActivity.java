package com.example.urecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_activity);
        getSupportActionBar().hide();

        new Handler().postDelayed(() -> {
            //If the user is not logged in, show this activity
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentUser==null){
                Intent intent = new Intent(OpeningActivity.this, OpeningActivity2.class);
                startActivity(intent);
            }else{
                //If the user is logged in, show the main page of the application
                Intent intent = new Intent(OpeningActivity.this, MainActivity.class);
                startActivity(intent);
            }
            finish();
            //Show the Opening Page in milliseconds
        }, 2000);

    }
}