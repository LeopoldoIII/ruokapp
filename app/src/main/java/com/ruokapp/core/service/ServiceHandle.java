package com.ruokapp.core.service;

import android.os.StrictMode;

import com.ruokapp.core.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServiceHandle {

    private URL url;
    private HttpURLConnection connection;
    private BufferedReader bufferedReader;

    private String inputLine;
    private StringBuffer response = new StringBuffer();
    private JSONObject jsonObject;
    private JSONArray recipes;

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

        url = new URL(ServiceHelper.getRecipes());
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(ServiceHelper.METHOD_GET);
        connection.connect();
        bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        System.out.println(connection.getResponseCode());
    }

    public void getRecipe(){

        try {
            connection();
            while ((inputLine = bufferedReader.readLine())!=null){
                response.append(inputLine);
            }
            jsonObject = new JSONObject(response.toString());

            recipes = jsonObject.getJSONArray("recipes");
            for(int j=0;j<recipes.length();j++){
                Recipe.getInstance().setId(recipes.getJSONObject(j).optInt("id"));
                Recipe.getInstance().setTitle(recipes.getJSONObject(j).optString("title"));
                Recipe.getInstance().setImage(recipes.getJSONObject(j).optString("image"));
                Recipe.getInstance().setReadyInMinutes(recipes.getJSONObject(j).optString("readyInMinutes"));
            }
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
