package com.ruokapp.core.recipe;

import java.util.ArrayList;

public class RecipeInfo {

    private static String id;
    private static String title;
    private static String imageUrl;
    private static String preparation;
    private static ArrayList<StepPreparation> stepPreparations;
    private static ArrayList<RecipeIngredient> recipeIngredients;
    private static RecipeInfo recipeInfo;

    public static RecipeInfo getInstance(){
        if (recipeInfo == null)
            recipeInfo = new RecipeInfo();
        return recipeInfo;
    }

    public static void setRecipeInfo(String idRecipe, String titleRecipe, String imageRecipe,
                                     ArrayList<StepPreparation> steps,
                                     ArrayList<RecipeIngredient> ingredients){
        id = idRecipe;
        title = titleRecipe;
        imageUrl = imageRecipe;
        stepPreparations = steps;
        recipeIngredients = ingredients;

    }

    public static String getId() {
        return id;
    }

    public static String getTitle() {
        return title;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    public static String getPreparation() {
        return preparation;
    }

    public static ArrayList<StepPreparation> getStepPreparations() {
        return stepPreparations;
    }

    public static ArrayList<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }
}
