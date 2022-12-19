package com.example.urecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.urecipe.account.AccountActivity;
import com.example.urecipe.explore.ExploreActivity;
import com.example.urecipe.login.LoginActivity;
import com.example.urecipe.message.MessageActivity;
import com.example.urecipe.myfav.MyFavActivity;
import com.example.urecipe.myrecipe.MyRecipeActivity;
import com.example.urecipe.settings.SettingsActivity;
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

        imageButton1 = (ImageButton) findViewById(R.id.my_recipe_btn);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My Recipe", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MyRecipeActivity.class);
                startActivity(intent);
            }
        });

        imageButton2 = (ImageButton) findViewById(R.id.my_fav_btn);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My Favourite", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MyFavActivity.class);
                startActivity(intent);
            }
        });

        imageButton3 = (ImageButton) findViewById(R.id.my_acc_btn);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My Account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}