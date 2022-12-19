package com.example.urecipe.settings;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.explore.ExploreActivity;
import com.example.urecipe.message.MessageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    TextView aboutUsTxt;
    TextView privacyTxt;
    TextView deleteAccTxt;

    Dialog dialog;

    @SuppressLint({"UseCompatLoadingForDrawables", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Settings");

        aboutUsTxt = findViewById(R.id.about_us_txt);
        aboutUsTxt.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });

        privacyTxt = findViewById(R.id.privacy_txt);
        privacyTxt.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, PrivacyActivity.class);
            startActivity(intent);
        });

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

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

        dialog = new Dialog(SettingsActivity.this);
        dialog.setContentView(R.layout.settings_delete_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        deleteAccTxt = findViewById(R.id.del_acc_txt);
        deleteAccTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        Button cancel = dialog.findViewById(R.id.cancel_btn);
        Button confirm = dialog.findViewById(R.id.confirm_btn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, "Delete Account", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}