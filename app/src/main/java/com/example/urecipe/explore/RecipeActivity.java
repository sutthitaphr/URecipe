package com.example.urecipe.explore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.urecipe.R;

public class RecipeActivity extends AppCompatActivity {
    //This activity retrieves all the recipe titles and data
    TextView vRecipeName;
    TextView vTimeTitle;
    TextView vRecipeTime;
    TextView vIngredTitle;
    TextView vIngred;
    TextView vProcedTitle;
    TextView vProced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_recipe);

        vRecipeName = (TextView)findViewById(R.id.recipe_name_view);
        vTimeTitle = (TextView)findViewById(R.id.time_title);
        vRecipeTime = (TextView)findViewById(R.id.recipe_time_view);
        vIngredTitle = (TextView)findViewById(R.id.ingred_title);
        vIngred = (TextView)findViewById(R.id.recipe_ingre_view);
        vProcedTitle = (TextView)findViewById(R.id.proced_title);
        vProced = (TextView)findViewById(R.id.recipe_proce_view);

        //Sending recipe data between explore activities
        Intent intent = getIntent();
        String reName = intent.getExtras().getString("RecipeName");
        String timeTitle = intent.getExtras().getString("TimeTile");
        String reTime = intent.getExtras().getString("RecipeTime");
        String ingreTitle = intent.getExtras().getString("IngredientsTitle");
        String reIngred = intent.getExtras().getString("RecipeIngredients");
        String proceTitle = intent.getExtras().getString("ProceduresTitle");
        String reProced = intent.getExtras().getString("RecipeProcedures");

        vRecipeName.setText(reName);
        vTimeTitle.setText(timeTitle);
        vRecipeTime.setText(reTime);
        vIngredTitle.setText(ingreTitle);
        vIngred.setText(reIngred);
        vProcedTitle.setText(proceTitle);
        vProced.setText(reProced);

    }
}