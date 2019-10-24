package com.ruokapp.core.service;

import android.os.StrictMode;

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

    private static URL url;
    private static HttpURLConnection connection;
    private static BufferedReader bufferedReader;

    private static String inputLine;
    private static StringBuffer response = new StringBuffer();
    private static JSONObject jsonObject;
    private static JSONArray recipes;

    private static void connection() throws IOException {
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

    public static JSONArray getRecipe(){

        try {
            connection();
            while ((inputLine = bufferedReader.readLine())!=null){
                response.append(inputLine);
            }
            jsonObject = new JSONObject(response.toString());

            recipes = jsonObject.getJSONArray("recipes");
//            for(int j=0;j<recipes.length();j++){
//                //System.out.println(recipes.getJSONObject(j).optString("id"));
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return recipes;
        }
    }
}
