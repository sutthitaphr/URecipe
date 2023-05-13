package com.example.urecipe.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.explore.ExploreActivity;
import com.example.urecipe.message.MessageActivity;
import com.example.urecipe.signup.SignUpActivity;
import com.example.urecipe.video.VideoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    TextView aboutUsTxt;
    TextView privacyTxt;
    TextView deleteAccTxt;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Switch aSwitch;
    boolean darkMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint({"UseCompatLoadingForDrawables", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Settings");

        aSwitch = findViewById(R.id.dark_mode_switch);
        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);
        darkMode = sharedPreferences.getBoolean("dark", false);

        if(darkMode){
            aSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        //Create method for Dark Mode option in the settings
        aSwitch.setOnClickListener(v -> {
            if(darkMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("dark", false);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("dark", true);
            }
            editor.apply();
        });

        //Open About Us page when clicked
        aboutUsTxt = findViewById(R.id.about_us_txt);
        aboutUsTxt.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });

        //Open Privacy page when clicked
        privacyTxt = findViewById(R.id.privacy_txt);
        privacyTxt.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, PrivacyActivity.class);
            startActivity(intent);
        });

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_video:
                    startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_explore:
                    startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_settings:
                    return true;
            }
            return false;
        });

        //If the user is logged into the application
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        //They can delete their account in the settings
        deleteAccTxt = findViewById(R.id.del_acc_txt);
        deleteAccTxt.setOnClickListener(v -> {
            //Display a dialog for confirmation
            AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
            dialog.setMessage("Are you sure you want to delete your account? This will permanently erase all data associate with this account.");
            //Delete an account from Firebase
            dialog.setPositiveButton("Delete Account", (dialog1, which) -> firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        //If the account has been deleted, show this message
                        Toast.makeText(SettingsActivity.this, "Your account has been deleted!", Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                        //Once deleted, display the sign up page from the application
                        Intent intent = new Intent(SettingsActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SettingsActivity.this, "Something went wrong while deleting an account!", Toast.LENGTH_SHORT).show();
                    }
                }
            }));

            //Show cancel option in the dialog, dismiss when clicked
            dialog.setNegativeButton("Cancel", (dialog12, which) -> dialog12.dismiss());
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        });
    }
}