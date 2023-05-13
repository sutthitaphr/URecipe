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

import java.util.Objects;

public class MyFavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fav_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Favourite");
    }
}