package com.example.urecipe.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.signup.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailTxt;
    EditText passwordTxt;
    TextView signUpTxt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        emailTxt = findViewById(R.id.email_text);
        passwordTxt = findViewById(R.id.password_text);
        signUpTxt = findViewById(R.id.sign_up_link);

        //Open Sign Up page when clicked
        signUpTxt.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener((v -> loginAccount()));
    }

    void loginAccount(){

        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        //If the validation is false, return

        boolean isValidated = validateData(email,password);
        if(!isValidated){
            return;
        }

        //If the validation is true, log the user in with Firebase
        loginAccountInFirebase(email,password);
    }

    void loginAccountInFirebase(String email, String password){
        //Create a firebase method to log the user into their account
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                //If the email is verified, user can log into the main page
                if(firebaseAuth.getCurrentUser().isEmailVerified()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    //If the email has not been verified, user cannot login
                    Toast.makeText(LoginActivity.this, "Email has not been verified!",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(LoginActivity.this, "Something went wrong while login in!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Validate the user email and Password
    boolean validateData(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTxt.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordTxt.setError("Min password length should be 7 characters!");
            return false;
        }
        return true;
    }
}