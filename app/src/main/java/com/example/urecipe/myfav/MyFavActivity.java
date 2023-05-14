package com.example.urecipe.myfav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.myrecipe.MyRecipeActivity;
import com.example.urecipe.video.VideoActivity;
import com.example.urecipe.video.VideoRecipeActivity;

import java.util.Objects;

public class MyFavActivity extends AppCompatActivity {

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fav_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Favourite");

        cardView = (CardView) findViewById(R.id.fav_cardview);
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(MyFavActivity.this, VideoRecipeActivity.class);
            startActivity(intent);
        });
    }
}