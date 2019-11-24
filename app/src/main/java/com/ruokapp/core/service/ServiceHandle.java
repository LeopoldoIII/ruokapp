package com.ruokapp.core.service;

import android.os.StrictMode;

import com.ruokapp.core.recipe.Recipe;
import com.ruokapp.core.recipe.RecipeInfo;
import com.ruokapp.core.recipe.RecipeRef;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ServiceHandle {

    private URL url;
    private HttpURLConnection connection;
    private BufferedReader bufferedReader;

    private String inputLine;
    private StringBuffer response = new StringBuffer();
    private JSONObject jsonObject;

    private static ServiceHandle serviceHandle;

    public static ServiceHandle getInstance(){
        if (serviceHandle == null){
            serviceHandle = new ServiceHandle();
        }
        return serviceHandle;
    }

    public void connection() throws IOException {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.
                        Builder().permitAll().build());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(ServiceHelper.METHOD_GET);
        connection.connect();
        bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        System.out.println(connection.getResponseCode());
    }

    public void getRecipe(String[] tags){
        try {
            url = new URL(ServiceHelper.getRecipes(tags));
            connection();
            obtainRecipe();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void obtainRecipe() throws IOException, JSONException{

            while ((inputLine = bufferedReader.readLine())!=null){
                response.append(inputLine);
            }
            jsonObject = new JSONObject(response.toString());

            JSONArray recipes = jsonObject.getJSONArray("recipes");
            for(int j=0;j<recipes.length();j++){
                Recipe.getInstance().setId(recipes.getJSONObject(j).optInt("id"));
                Recipe.getInstance().setTitle(recipes.getJSONObject(j).optString("title"));
                Recipe.getInstance().setImage(recipes.getJSONObject(j).optString("image"));
                Recipe.getInstance().setReadyInMinutes(recipes.getJSONObject(j).optString("readyInMinutes"));
            }
    }

    public void getRecipe(){
        try {
            url = new URL(ServiceHelper.getRecipes());
            connection();
            obtainRecipe();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RecipeRef> getFavoritesRecipes(String ids){
        ArrayList<RecipeRef> recipeRefs = null;
        try {
            url = new URL(ServiceHelper.getInformationBulk(ids));
            connection();
            recipeRefs = obtainRecipesRef();
            closeConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return recipeRefs;
        }
    }

    private ArrayList<RecipeRef> obtainRecipesRef() throws IOException, JSONException{
        ArrayList<RecipeRef> recipeRefs = new ArrayList<>();

        while ((inputLine = bufferedReader.readLine())!=null){
            response.append(inputLine);
        }
        JSONArray jsonObject = new JSONArray(response.toString());
        for (int i=0; i<jsonObject.length();i++){
            JSONObject info = jsonObject.getJSONObject(i);
            RecipeRef recipeRef = new RecipeRef(info.optInt("id"),
                    info.optString("title"),
                    info.optString("image"),
                    info.optString("readyInMinutes"));
            recipeRefs.add(recipeRef);
        }
        return recipeRefs;
    }

    public void getRecipeInfo(String idRecipe){

        try {
            url = new URL(ServiceHelper.getInformationRecipe(idRecipe));
            connection();
            while ((inputLine = bufferedReader.readLine())!=null){
                response.append(inputLine);
            }
            jsonObject = new JSONObject(response.toString());
            JSONArray jsonIngredients = jsonObject.getJSONArray("extendedIngredients");
            String ingredients = "";
            for(int i=0; i<jsonIngredients.length();i++){
                ingredients += jsonIngredients.getJSONObject(i).optString("originalString")+"\n\n";
            }
            RecipeInfo.getInstance().setRecipeInfo (jsonObject.optString("id"),
                    jsonObject.optString("title"),
                    jsonObject.optString("image"),
                    jsonObject.optString("readyInMinutes"),
                    jsonObject.optString("instructions"),
                    ingredients,
                    jsonObject.optString("sourceUrl"));


            closeConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection(){
        connection.disconnect();
        serviceHandle = null;
    }
}
