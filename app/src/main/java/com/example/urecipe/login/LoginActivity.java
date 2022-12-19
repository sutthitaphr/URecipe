package com.example.urecipe.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.signup.SignUpActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextView textView;
    EditText username;
    EditText password;
    Button loginBtn;

    //Connect to Realtime database
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://urecipe-d8035-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        textView = (TextView) findViewById(R.id.sign_up_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        username = findViewById(R.id.username_text);
        password = findViewById(R.id.password_text);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = username.getText().toString();
                String passwordInput = password.getText().toString();

                if(usernameInput.isEmpty() || passwordInput.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
                }

                //Check password length
                if(passwordInput.length() < 7){
                    password.setError("Min password length should be 7 characters!");
                    password.requestFocus();
                    return;
                }
                databaseRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Check for a already registered username
                        if(snapshot.hasChild(usernameInput)){

                            //Get the user password and match them if the user is registered in the database
                            String getPassword = snapshot.child(usernameInput).child("password").getValue(String.class);

                            if(getPassword.equals(passwordInput)){
                                Toast.makeText(LoginActivity.this, "You are logged in!", Toast.LENGTH_LONG).show();

                                //Opens main activity
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Wrong Password!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Failed to Login!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}