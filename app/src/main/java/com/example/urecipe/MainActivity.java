package com.example.urecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.urecipe.account.AccountActivity;
import com.example.urecipe.explore.ExploreActivity;
import com.example.urecipe.message.MessageActivity;
import com.example.urecipe.myfav.MyFavActivity;
import com.example.urecipe.myrecipe.MyRecipeActivity;
import com.example.urecipe.settings.SettingsActivity;
import com.example.urecipe.video.VideoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
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
                        return true;
                    case R.id.nav_message:
                        startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
        });

        //Open My Recipe page when clicked
        imageButton1 = (ImageButton) findViewById(R.id.my_recipe_btn);
        imageButton1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MyRecipeActivity.class);
            startActivity(intent);
        });

        //Open My Favourite page when clicked
        imageButton2 = (ImageButton) findViewById(R.id.my_fav_btn);
        imageButton2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MyFavActivity.class);
            startActivity(intent);
        });

        //Open My Account page when clicked
        imageButton3 = (ImageButton) findViewById(R.id.my_acc_btn);
        imageButton3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(intent);
        });
    }
}