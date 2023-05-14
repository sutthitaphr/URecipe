package com.example.urecipe.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.explore.ExploreActivity;
import com.example.urecipe.settings.SettingsActivity;
import com.example.urecipe.video.VideoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MessageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_main);
        getSupportActionBar().setTitle("Message");

        //Display navigation bar
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_message);
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
                    return true;
                case R.id.nav_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        //Display message when clicked
        cardView = findViewById(R.id.message_card);
        cardView.setOnClickListener(view -> {
            Intent intent = new Intent(MessageActivity.this, WelcomeActivity.class);
            startActivity(intent);
        });
    }
}