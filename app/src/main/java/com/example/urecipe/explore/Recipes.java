package com.example.urecipe.explore;

public class Recipes {

    String name;
    String timeT;
    String time;
    String ingreT;
    String ingredients;
    String proceT;
    String procedures;
    int image;

    public Recipes(String recipeName, String timeTitle, String recipeTime, String ingreTitle, String recipeIngredients, String proceTitle, String recipeProcedures, int recipeImage){
        name = recipeName;
        timeT = timeTitle;
        time = recipeTime;
        ingreT = ingreTitle;
        ingredients = recipeIngredients;
        proceT = proceTitle;
        procedures = recipeProcedures;
        image = recipeImage;
    }

    public String getName() {
        return name;
    }

    public String getTimeT() {
        return timeT;
    }

    public String getTime() {
        return time;
    }

    public String getIngreT() {
        return ingreT;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProceT() {
        return proceT;
    }

    public String getProcedures() {
        return procedures;
    }

    public int getImage() {
        return image;
    }
}
