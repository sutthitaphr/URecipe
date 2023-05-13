package com.example.urecipe.myrecipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urecipe.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RecipeAdapter extends FirestoreRecyclerAdapter<Recipe, RecipeAdapter.RecipeCardHolder> {

    Context context;

    public RecipeAdapter(@NonNull FirestoreRecyclerOptions<Recipe> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecipeCardHolder holder, int position, @NonNull Recipe recipe) {

        holder.recipeName.setText(recipe.name);
        holder.timeStamp.setText(RecipeDatabase.timestamp(recipe.timestamp));
        //Display recipes under unique ID
        holder.itemView.setOnClickListener((v) ->{
            Intent intent = new Intent(context,RecipeDetailsActivity.class);
            intent.putExtra("name",recipe.name);
            intent.putExtra("time",recipe.time);
            intent.putExtra("ingredients",recipe.ingredients);
            intent.putExtra("procedures",recipe.procedures);

            String recipeId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("recipeId",recipeId);
            context.startActivity(intent);
        });
    }

    //Pass the recipe data from the view holder
    @NonNull
    @Override
    public RecipeCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recipe_card,parent,false);
        return new RecipeCardHolder(view);
    }

    //Hold the recipe view
    class RecipeCardHolder extends RecyclerView.ViewHolder{

        TextView recipeName;
        TextView timeStamp;

        public RecipeCardHolder(@NonNull View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.nameTxt);
            timeStamp = itemView.findViewById(R.id.time_created);
        }
    }
}
