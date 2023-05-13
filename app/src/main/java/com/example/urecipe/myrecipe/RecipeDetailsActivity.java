package com.example.urecipe.myrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.urecipe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class RecipeDetailsActivity extends AppCompatActivity {

    EditText nameTxt;
    EditText timeTxt;
    EditText ingredientsTxt;
    EditText proceduresTxt;
    ImageButton saveBtn;
    String name;
    String time;
    String ingredients;
    String procedures;
    String recipeId;
    boolean editRecipe = false;
    ImageButton deleteRecipeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recipe_details);

        nameTxt = findViewById(R.id.recipe_name_txt);
        timeTxt = findViewById(R.id.recipe_time_txt);
        ingredientsTxt = findViewById(R.id.ingred_txt);
        proceduresTxt = findViewById(R.id.proce_txt);
        saveBtn = findViewById(R.id.save_btn);
        deleteRecipeBtn = findViewById(R.id.delete_recipe);

        name = getIntent().getStringExtra("name");
        time = getIntent().getStringExtra("time");
        ingredients = getIntent().getStringExtra("ingredients");
        procedures = getIntent().getStringExtra("procedures");
        recipeId = getIntent().getStringExtra("recipeId");

        //User can edit and update the recipe
        if(recipeId!=null && recipeId.isEmpty()){
            editRecipe = true;
        }

        nameTxt.setText(name);
        timeTxt.setText(time);
        ingredientsTxt.setText(ingredients);
        proceduresTxt.setText(procedures);

        saveBtn.setOnClickListener((v) -> saveRecipe());

        deleteRecipeBtn.setOnClickListener((v) -> deleteRecipeFromFirebase());
    }

    //Retrieve the recipe data
    void saveRecipe(){
        String recipeName = nameTxt.getText().toString();
        String recipeTime = timeTxt.getText().toString();
        String ingred = ingredientsTxt.getText().toString();
        String proce = proceduresTxt.getText().toString();

        //Validate the recipe
        if(recipeName==null || recipeName.isEmpty()){
            nameTxt.setError("Recipe Name is required!");
            return;
        }

        //Add to model class
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setTime(recipeTime);
        recipe.setIngredients(ingred);
        recipe.setProcedures(proce);
        recipe.setTimestamp(Timestamp.now());

        saveRecipeToFirebase(recipe);
    }

    //Save the recipes created to the Firebase Database
    void saveRecipeToFirebase(Recipe recipe){
        //Save the recipes as a document in the collection
        DocumentReference documentReference;
        //Update the recipe
        if(editRecipe){
            //If the recipe is edited, update the recipe
            documentReference = RecipeDatabase.getCollectionReferenceForRecipes().document(recipeId);
        }else{
            documentReference = RecipeDatabase.getCollectionReferenceForRecipes().document();
        }

        documentReference.set(recipe).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                //Recipe is added in the Firebase
                Toast.makeText(RecipeDetailsActivity.this, "New recipe added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(RecipeDetailsActivity.this, "Failed to add new recipe!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Create method to delete recipe from the database
    private void deleteRecipeFromFirebase() {
        DocumentReference documentReference;
        documentReference = RecipeDatabase.getCollectionReferenceForRecipes().document(recipeId);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Recipe is deleted in the Firebase
                    Toast.makeText(RecipeDetailsActivity.this, "Recipe has been deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RecipeDetailsActivity.this, "Failed to delete recipe!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}