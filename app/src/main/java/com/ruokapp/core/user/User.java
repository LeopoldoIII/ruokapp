package com.ruokapp.core.user;

import com.ruokapp.core.recipe.RecipeRef;

import java.util.ArrayList;

public class User {

    private long id;
    private String name;
    private String email;
    private ArrayList<RecipeRef> recipeRefs = new ArrayList<>();
    private Preference preference = new Preference();
    private static User user = null;

    private static void createUser() {
        user = new User();
    }

    public static User getInstanceUser(){
        if(user == null){
            createUser();
        }
        return user;
    }

    public void setUser(long id, String username, String email){
        this.id = id;
        this.name = username;
        this.email = email;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public Preference getPreference(){
        return preference;
    }

    public void setRecipeRefs(ArrayList<RecipeRef> recipeRefs){
        this.recipeRefs = recipeRefs;
    }

    public ArrayList<RecipeRef> getRecipeRefs(){
        return recipeRefs;
    }

}
