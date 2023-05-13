package com.example.urecipe.account;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.urecipe.R;
import com.example.urecipe.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AccountActivity extends AppCompatActivity {

    Button logoutBtn;
    TextView emailTxt;

    //Firebase Authentication & User
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Account");

        //Retrieves an instance and the current user
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        //Retrieve the user's email to display in the account page.
        emailTxt = findViewById(R.id.email_txt);
        emailTxt.setText(firebaseUser.getEmail());

        logoutBtn = findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(v -> {
            if(v.getId() == R.id.logout_btn){
                //Signs the user out of the firebase and the application
                FirebaseAuth.getInstance().signOut();
                //If the current user logged out, show the message
                Toast.makeText(AccountActivity.this, "Log Out Successfully!",Toast.LENGTH_SHORT).show();

                //Show the user login once signed out
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(AccountActivity.this, "Something went wrong while logging out!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}