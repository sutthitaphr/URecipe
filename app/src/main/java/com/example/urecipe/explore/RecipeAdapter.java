package com.example.urecipe.explore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urecipe.R;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.Holder> {
    //This adapter is to hold the recipe data

    Context context;
    List<Recipes> recipesList;

    public  RecipeAdapter(Context context, List<Recipes> recipesList){
        this.context = context;
        this.recipesList = recipesList;
    }

    //Creating a recipe view holder for the recipes
    @NonNull
    @Override
    public RecipeAdapter.Holder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.explore_card_view,group,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.Holder holder, int i) {

        holder.recipeName.setText(recipesList.get(i).getName());
        holder.imageView.setImageResource(recipesList.get(i).getImage());
        holder.cardView.setOnClickListener(v -> {

            //Add extended data to the intent
            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("RecipeName",recipesList.get(i).getName());
            intent.putExtra("TimeTile",recipesList.get(i).getTimeT());
            intent.putExtra("RecipeTime",recipesList.get(i).getTime());
            intent.putExtra("IngredientsTitle",recipesList.get(i).getIngreT());
            intent.putExtra("RecipeIngredients",recipesList.get(i).getIngredients());
            intent.putExtra("ProceduresTitle",recipesList.get(i).getProceT());
            intent.putExtra("RecipeProcedures",recipesList.get(i).getProcedures());
            intent.putExtra("Image",recipesList.get(i).getImage());

            //Start the recipes detail activity
            context.startActivity(intent);
        });
    }

    //Return the recipes in the adapter
    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    //Display data on the card view
    public class Holder extends RecyclerView.ViewHolder{
        TextView recipeName;
        CardView cardView;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            recipeName = (TextView)itemView.findViewById(R.id.recipe_card_name);
            cardView = (CardView)itemView.findViewById(R.id.recipe_cardview);
            imageView = (ImageView)itemView.findViewById(R.id.recipe_card_image);
        }
    }
}
