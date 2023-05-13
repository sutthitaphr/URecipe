package com.example.urecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.urecipe.login.LoginActivity;
import com.example.urecipe.signup.SignUpActivity;

public class OpeningActivity2 extends AppCompatActivity {

    Button loginBtn;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_activity2);
        getSupportActionBar().hide();

        loginBtn = (Button) findViewById(R.id.op_login_btn);
        signUpBtn = (Button) findViewById(R.id.op_sign_up_btn);

        //Show options between Log In or Sign Up in the opening page of the application
        loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(OpeningActivity2.this, LoginActivity.class);
            startActivity(intent);
        });

        signUpBtn.setOnClickListener(view -> {
            Intent intent = new Intent(OpeningActivity2.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}