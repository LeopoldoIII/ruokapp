package com.ruokapp.core.service;

public class ServiceHelper {

    public static final String METHOD_GET = "GET";

    private static final String API_KEY = "474543c0a62f472dab6cd9daaf029704";

    private static final String API_KEY_PARAM = "&apiKey=";

    private static final String GET_RANDOM_RECIPES_URL = "https://api.spoonacular.com/recipes/random?number=1";


    public static String getRecipes(){
        return String.format(
                "%s%s%s",GET_RANDOM_RECIPES_URL,API_KEY_PARAM,API_KEY);
    }


}
