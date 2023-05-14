package com.example.urecipe.explore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.urecipe.MainActivity;
import com.example.urecipe.R;
import com.example.urecipe.message.MessageActivity;
import com.example.urecipe.settings.SettingsActivity;
import com.example.urecipe.signup.SignUpActivity;
import com.example.urecipe.video.VideoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    List<Recipes> recipesL;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_explore);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_video:
                    startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_explore:
                    return true;
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
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

        //Create an array list for the recipes
        recipesL = new ArrayList<>();
        recipesL.add(new Recipes("Frito Pie","Time:","5 minutes","Ingredients:","1 cup corn chips\n" +
                "1 cup prepared chili\n" +
                "1⁄4 cup shredded cheddar cheese\n" +
                "2 teaspoons chopped onions (optional)\n" +
                "mustard (optional)","Procedures:","Heat the chili until warm either in a pot on the stove or in a microwave.\n" +
                "Place corn chips in a bowl.\n" +
                "Ppour over chili then top with the cheese, and onions (optional).\n" +
                "Finish with a squirt of mustard.",R.drawable.frito));

        recipesL.add(new Recipes("Oven Fried Pickles","Time:", "27 minutes","Ingredients:", "1 (24 oz) jar pickles, slices\n" +
                "2 eggs\n" +
                "1⁄3 cup flour\n" +
                "1 tablespoon Worcestershire sauce\n" +
                "1 teaspoon hot sauce\n" +
                "1 teaspoon garlic powder\n" +
                "1 teaspoon cajun seasoning\n" +
                "1 teaspoon pepper\n" +
                "1 1⁄2 cups panko breadcrumbs\n" +
                "ranch dressing, and hot sauce for dipping","Procedures:","Turn oven broiler on high.\n" +
                "Whisk the eggs and flour in a bowl.\n" +
                "Add Worcestershire sauce, hot sauce, garlic powder, Cajun seasoning, and pepper. Mix well.\n" +
                "Place panko bread crumbs in a shallow dish.\n" +
                "Dunk each pickle slice into the egg mixture, than dredge it in the panko bread crumbs.\n" +
                "Place coated pickles on a rack set above a baking sheet and sprayed with non-stick cooking spray.\n" +
                "Place pan in the middle rack of the oven.\n" +
                "Broil for about 3 minutes on each side.\n" +
                "Serve with Ranch dressing and a dash of hot sauce.",R.drawable.pickles));

        recipesL.add(new Recipes("Brownies","Time:", "40 - 50 minutes", "Ingredients:","8 ounces good-quality chocolate \n" +
                "¾ cup butter, melted\n" +
                "1¼ cups sugar\n" +
                "2 eggs\n" +
                "2 teaspoons vanilla\n" +
                "¾ cup all-purpose flour\n" +
                "¼ cup cocoa powder\n" +
                "1 teaspoon salt","Procedures:","Preheat oven to 350°F/180°C.\n" +
                "Chop the chocolate into chunks. Melt half, then save the other half for later.\n" +
                "Mix the butter and the sugar, then beat in the eggs and vanilla for 1-2 minutes until the mixture has become fluffy and light in color.\n" +
                "Whisk in the reserved melted chocolate (make sure the chocolate is not too hot or else the eggs will cook), then sift in the flour, cocoa powder, and salt.\n" +
                "Fold the dry ingredients into the wet ingredients, being careful not to overmix as this will cause the brownies to be more cake-like in texture.\n" +
                "Fold in the chocolate chunks, then transfer the batter into a parchment paper-lined square baking dish.\n" +
                "Bake for 20-25 minutes, depending on how fudgy you like your brownies, then cool completely.\n" +
                "Slice, then serve with a nice cold glass of milk!", R.drawable.home_bg4));

        recipesL.add(new Recipes("Watergate Salad","Time:", "1hr 10 minutes","Ingredients:", "(3 1/2 ounce) package instant pistachio pudding mix\n" +
                "1 (20 ounce) can crushed pineapple with juice, undrained\n" +
                "1 cup miniature marshmallow\n" +
                "1⁄2 cup chopped nuts\n" +
                "2 cups thawed non-dairy whipped topping","Procedures:","Stir pudding mix, pineapple with juice, marshmallows and nuts in large bowl until well blended.\n" +
                "Gently stir in whipped topping.\n" +
                "Refrigerate 1 hour or until ready to serve.\n" +
                "Garnish as desired.",R.drawable.watergate_salad));

        recipesL.add(new Recipes("Banana Cake","Time:", "50 minutes","Ingredients:", "40 grams melted butter\n" +
                "40 grams sugar\n" +
                "1 large banana\n" +
                "1 tablespoon milk\n" +
                "1/4 teaspoon vanilla extract\n" +
                "1 egg\n" +
                "80 grams cake flour\n" +
                "1/4 teaspoon baking soda\n" +
                "1/2 teaspoon baking powder\n","Procedures:","Preheat the oven to 170C/160C Fan/Gas 4.\n" +
                "Greas and line a tin with baking parchment or use a loaf tin liner.\n" +
                "Peel and mash the banana with a fork in a bowl.\n" +
                "Pour the melted butter and sugar in the banana bowl. Mix well.\n" +
                "Add the vanilla extract, milk and egg in. Mix well\n" +
                "Add the baking soda and baking powder with the flour and sieve in the bowl.\n" +
                "Mix the ingredients together and pour in the tin.\n" +
                "Bake for 35 minutes or untile the cake is well risen.\n" +
                "Cool in the tin for 10 minutes, then turn out onto a wire rack.\n" +
                " Serve warm or cold in slices.",R.drawable.banana_cake));

        recipesL.add(new Recipes("Souffle Pancake","Time:", "30 minutes","Ingredients:", "2 large eggs, separated\n" +
                "2 tbsp golden caster sugar\n" +
                "1 tsp vanilla extract\n" +
                "2 tbsp milk\n" +
                "2 tbsp self-raising flour\n" +
                "1 tsp vegetable oil\n" +
                "butter, icing sugar and maple syrup, to serve","Procedures:","Whisk the egg whites in a clean bowl with 1 tbsp sugar using an electric whisk or a stand mixer to form stiff peaks.\n" +
                "Beat the egg yolks, 1 tbsp sugar and vanilla together in a separate bowl until pale and foamy.\n" +
                "Gently fold in the milk and flour until just incorporated.\n" +
                "Fold the egg whites into the egg yolk mixture and gently turn the batter over to mix, using the side of a metal spoon or spatula.\n" +
                "Heat a large non-stick frying pan with a lid over a very low heat.\n" +
                "Drizzle a little oil into the pan, then wipe it with a piece of kitchen paper.\n" +
                "Make three tall pancakes by piling three spoonfuls of the batter into the pan.\n" +
                "Keep them piled quite high.\n" +
                "Cover with a lid and cook for 2-4 mins, the steam will help them set.\n" +
                "Remove the lid and add another dollop of batter to each pancake.\n" +
                "Return the lid and cook for another 4-6 mins until the top feels slightly set.\n" +
                "Very gently, and carefully, turn the pancakes over in the pan with a fish slice or spatula.\n" +
                "The bases should be a light golden brown.\n" +
                "Add the lid back on and cook for another 4-6 mins until both sides are golden brown and they have a slight wobble, but are not collapsing or sticky.\n",R.drawable.souffle_cake));

        //Get the recycler view
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recipeAdapter = new RecipeAdapter(this,recipesL);
        //Display the recipes in a grid format
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(recipeAdapter);
    }
}