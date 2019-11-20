package com.ruokapp.core.service;

public class ServiceHelper {

    public static final String METHOD_GET = "GET";

    private static final String API_KEY = "474543c0a62f472dab6cd9daaf029704";

    private static final String API_KEY_PARAM = "&apiKey=";

    private static final String GET_RANDOM_RECIPES_URL = "https://api.spoonacular.com/recipes/random?number=1";

    private static final String GET_INFORMATION_BULK_URL = "https://api.spoonacular.com/recipes/informationBulk?ids=";

    private static final String GET_INFORMATION_RECIPE = "https://api.spoonacular.com/recipes/";

    private static final String INFORMATION_PATH = "/information?apiKey=";

    private static final String TAGS = "&tags=";


    public static String getRecipes(){
        return String.format(
                "%s%s%s",GET_RANDOM_RECIPES_URL,API_KEY_PARAM,API_KEY);
    }

    public static String getRecipes(String[] tags){
        String result = getRecipes()+TAGS;
        for (int i=0; i<tags.length;i++) {
            if (tags[i] != null){
                result += tags[i]+",";
            }
        }
        return result;
    }

    public static String getInformationBulk(String ids){
        String url = String.format("%s%s%s%s",GET_INFORMATION_BULK_URL,ids,API_KEY_PARAM,API_KEY);
        return url;
    }

    public static String getInformationRecipe(String idRecipe){
        String url = String.format("%s%s%s%s",GET_INFORMATION_RECIPE,idRecipe,INFORMATION_PATH,API_KEY);
        return url;
    }

}
