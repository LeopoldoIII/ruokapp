package com.ruokapp.db;

import android.content.ContentValues;

import androidx.test.InstrumentationRegistry;

import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DiscoveryTest {

    ContentValues food = new ContentValues();
    ContentValues user = new ContentValues();

    @Test
    public void insertFoodRef(){
        String userName = "NewUser";
        String email = String.format("singUp+%s@test.com",new Date().getTime());
        String pass = "passTest";
        user.put(DBUtils.USER_NAME,userName );
        user.put(DBUtils.USER_EMAIL, email);
        user.put(DBUtils.USER_PASSWORD, pass);
        long idUser = SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(),user);
        int idFoodRef = 9999;
        food.put(DBUtils.ID_USER, idUser);
        food.put(DBUtils.ID_FOOD_REF, idFoodRef);
        long idFood = SQLiteHandler.insertFoodRef(InstrumentationRegistry.getTargetContext(),food);
        Assert.assertTrue("Error: The user ID is 0",idFood!=0);
    }
}
