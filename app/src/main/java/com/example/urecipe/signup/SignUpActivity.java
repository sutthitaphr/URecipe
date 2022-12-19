package com.example.urecipe.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urecipe.MainActivity;
import com.example.urecipe.OpeningActivity;
import com.example.urecipe.R;
import com.example.urecipe.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    TextView textView;
    Button signUpBtn;

    //Connect to Realtime database
    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://urecipe-d8035-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_main);

        textView = (TextView) findViewById(R.id.login_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        username = findViewById(R.id.username_text);
        email = findViewById(R.id.email_text);
        password = findViewById(R.id.password_text);

        signUpBtn = findViewById(R.id.sign_up_btn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = username.getText().toString();
                String emailInput = email.getText().toString();
                String passwordInput = password.getText().toString();

                if(usernameInput.isEmpty()){
                    username.setError("Username is required!");
                    username.requestFocus();
                    return;
                }
                if(emailInput.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }
                //Email validation
                if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
                    email.setError("Please provide a valid email!");
                    email.requestFocus();
                    return;
                }
                if(passwordInput.isEmpty()){
                    password.setError("Password is required!");
                    password.requestFocus();
                    return;
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
                            Toast.makeText(SignUpActivity.this, "This username is already registered!", Toast.LENGTH_LONG).show();
                        }
                        else{

                            //Put user details under their username
                            databaseRef.child("users").child(usernameInput).child("email").setValue(emailInput);
                            databaseRef.child("users").child(usernameInput).child("password").setValue(passwordInput);

                            Toast.makeText(SignUpActivity.this, "Sign Up has been Successfully!", Toast.LENGTH_LONG).show();
                            finish();

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);

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