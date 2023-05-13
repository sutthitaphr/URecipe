package com.example.urecipe.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urecipe.R;
import com.example.urecipe.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText emailTxt;
    EditText passwordTxt;
    EditText confirmPassTxt;
    TextView loginTxt;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_main);

        emailTxt = findViewById(R.id.email_text);
        passwordTxt = findViewById(R.id.password_text);
        confirmPassTxt = findViewById(R.id.password_confirm_text);
        loginTxt = findViewById(R.id.login_text);
        loginTxt.setOnClickListener(v -> {
            //Open Login Page when clicked
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        signUpBtn = findViewById(R.id.sign_up_btn);
        signUpBtn.setOnClickListener(v -> signUpAccount());
    }

    void signUpAccount() {
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        String confirmPass = confirmPassTxt.getText().toString();

        //If the validation is false, return
        boolean isValidated = validateData(email,password,confirmPass);
        if(!isValidated){
            return;
        }
        //If the validation is true, create the account in Firebase
        signUpAccountInFirebase(email,password);
    }

    void signUpAccountInFirebase(String email, String password){
        //Create a firebase method to create an account
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        //Provided by Firebase
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //If the account is created, show this message
                    Toast.makeText(SignUpActivity.this, "Sign Up has been Successfully! Verify your email",Toast.LENGTH_SHORT).show();
                    //Send the verification via email before logging in
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    //User cannot login before verify their email
                    firebaseAuth.signOut();
                    finish();
                }else{
                    //If the account is not created, show this message
                    Toast.makeText(SignUpActivity.this, "Something went wrong while signing up!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Validate the user email and Password
    boolean validateData(String email, String password, String confirmPass){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTxt.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordTxt.setError("Min password length should be 7 characters!");
            return false;
        }
        if(!password.equals(confirmPass)){
            confirmPassTxt.setError("Password not matched!");
            return false;
        }
        return true;
    }
}