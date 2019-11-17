package com.ruokapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.test.InstrumentationRegistry;

import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.recipe.Recipe;
import com.ruokapp.core.service.ServiceHandle;
import com.ruokapp.core.user.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class FavoritesTestDB {

    Context context = InstrumentationRegistry.getTargetContext();

    @Test
    public void getFavRecipe(){
        String userName = "NewUser";
        String email = String.format("singUp+%s@test.com",new Date().getTime());
        String pass = "passTest";
        ContentValues user = new ContentValues();
        user.put(DBUtils.USER_NAME,userName );
        user.put(DBUtils.USER_EMAIL, email);
        user.put(DBUtils.USER_PASSWORD, pass);
        long idUser = SQLiteHandler.insertUser(context,user);
        ServiceHandle.getInstance().getRecipe();
        ContentValues foodRef = new ContentValues();
        foodRef.put(DBUtils.ID_USER, idUser);
        foodRef.put(DBUtils.ID_FOOD_REF, Recipe.getInstance().getId());
        SQLiteHandler.insertFoodRef(context,foodRef);
        String[] fields = {DBUtils.ID_FOOD_REF};
        String[] params = {String.format("%s",idUser)};
        Cursor cursor = SQLiteHandler.getRecipesFromUser(context,fields,params);
        cursor.moveToFirst();
        Assert.assertEquals(Recipe.getInstance().getId(),cursor.getInt(0));
    }
}
