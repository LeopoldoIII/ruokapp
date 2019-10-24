package com.ruokapp.core.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

public class JsonHandler {

    private static HashMap<String,String> infoRecipe;

    public static HashMap<String,String> getRecipeInfo(JSONArray jsonArray){
        try {
            for (int i=0;i<jsonArray.length();i++){
                infoRecipe.put(FoodConstant.ID,jsonArray.getJSONObject(i).optString(FoodConstant.ID));
                infoRecipe.put(FoodConstant.ID,jsonArray.getJSONObject(i).optString(FoodConstant.ID));
                infoRecipe.put(FoodConstant.ID,jsonArray.getJSONObject(i).optString(FoodConstant.ID));
                infoRecipe.put(FoodConstant.ID,jsonArray.getJSONObject(i).optString(FoodConstant.ID));
                infoRecipe.put(FoodConstant.ID,jsonArray.getJSONObject(i).optString(FoodConstant.ID));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return infoRecipe;
        }
    }
}
