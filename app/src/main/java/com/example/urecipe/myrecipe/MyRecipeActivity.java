package com.example.urecipe.myrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.urecipe.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

import java.util.Objects;

public class MyRecipeActivity extends AppCompatActivity {

    FloatingActionButton addBtn;
    RecyclerView recipeListView;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recipe_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Recipe");

        addBtn = findViewById(R.id.add_recipe_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MyRecipeActivity.this, RecipeDetailsActivity.class);
            startActivity(intent);
        });

        recipeListView = findViewById(R.id.my_recipe_list);
        setRecipeListView();
    }

    //Retrieve recipes from the Firebase and display in the RecyclerView
    void setRecipeListView(){
        //Query the database from the collection
        Query query = RecipeDatabase.getCollectionReferenceForRecipes().orderBy("timestamp",Query.Direction.DESCENDING);
        //Return the recycler options from the query with recipes
        FirestoreRecyclerOptions<Recipe> options = new FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query,Recipe.class).build();

        recipeListView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(options, this);
        recipeListView.setAdapter(recipeAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recipeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recipeAdapter.stopListening();
    }

    //When the recipes are updated
    @Override
    protected void onResume() {
        super.onResume();
        recipeAdapter.notifyDataSetChanged();
    }
}