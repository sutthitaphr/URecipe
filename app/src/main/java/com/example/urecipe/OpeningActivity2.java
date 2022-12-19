package com.example.urecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.urecipe.login.LoginActivity;
import com.example.urecipe.myrecipe.MyRecipeActivity;
import com.example.urecipe.signup.SignUpActivity;

public class OpeningActivity2 extends AppCompatActivity {

    Button loginBtn;
    Button signUpBtn;
    ImageButton closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_activity2);
        getSupportActionBar().hide();

        loginBtn = (Button) findViewById(R.id.op_login_btn);
        signUpBtn = (Button) findViewById(R.id.op_sign_up_btn);
        closeBtn = (ImageButton) findViewById(R.id.close_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpeningActivity2.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpeningActivity2.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpeningActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}