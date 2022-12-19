package com.example.urecipe.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.explore.ExploreActivity;
import com.example.urecipe.settings.PrivacyActivity;
import com.example.urecipe.settings.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MessageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_main);
        getSupportActionBar().setTitle("Message");

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_message);

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
                    return true;

                case R.id.nav_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });


        cardView = findViewById(R.id.message_card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}