package com.example.urecipe.myrecipe;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class RecipeDatabase {

    //Create collection of recipes to store in the database for each user under their ID
    static CollectionReference getCollectionReferenceForRecipes(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("Recipes").document(firebaseUser.getUid()).collection("My_Recipes");
    }

    //Display time for the recipe created in  string
    static String timestamp(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }
}
