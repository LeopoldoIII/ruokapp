package com.ruokapp.db;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.test.InstrumentationRegistry;

import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class PreferenceDBTest {

    private long id;

    @Before
    public void setUp(){
        //data
        String name = "UserRegistered";
        String email = String.format("existing+%s@test.com", new Date().getTime());
        String pass = "pass.1234";

        // Pre-condition
        ContentValues data = new ContentValues();
        data.put(DBUtils.USER_NAME, name);
        data.put(DBUtils.USER_EMAIL, email);
        data.put(DBUtils.USER_PASSWORD, pass);
        id = SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(), data);
    }

    @Test
    public void userUpdatePreferences(){

        String[] params = {String.format("%s",id)};
        ContentValues preference = new ContentValues();
        int isVegetarian = 1;
        int isNotVegan = 0;
        int isGlutenFree = 1;
        preference.put(DBUtils.PREF_VEGETARIAN, isVegetarian);
        preference.put(DBUtils.PREF_VEGAN,isNotVegan);
        preference.put(DBUtils.PREF_GLUTEN_FREE,isGlutenFree);
        int register = SQLiteHandler.updatePreferences(InstrumentationRegistry.getTargetContext(),preference,params);
        Assert.assertEquals(1,register);
    }

    @Test
    public void invalidUserUpdatePreferences(){

        String[] params = {"-1"};
        ContentValues preference = new ContentValues();
        int isVegetarian = 1;
        int isNotVegan = 0;
        int isGlutenFree = 1;
        preference.put(DBUtils.PREF_VEGETARIAN, isVegetarian);
        preference.put(DBUtils.PREF_VEGAN,isNotVegan);
        preference.put(DBUtils.PREF_GLUTEN_FREE,isGlutenFree);
        int userId = SQLiteHandler.updatePreferences(InstrumentationRegistry.getTargetContext(),preference,params);
        Assert.assertEquals(0,userId);
    }

    @Test
    public void userSelectPreferences(){

        String[] params = {String.format("%s",id)};
        ContentValues preference = new ContentValues();
        int isVegetarian = 1;
        int isNotVegan = 0;
        int isGlutenFree = 1;
        preference.put(DBUtils.PREF_VEGETARIAN, isVegetarian);
        preference.put(DBUtils.PREF_VEGAN,isNotVegan);
        preference.put(DBUtils.PREF_GLUTEN_FREE,isGlutenFree);
        SQLiteHandler.updatePreferences(InstrumentationRegistry.getTargetContext(),preference,params);

        String[] fields = {DBUtils.PREF_VEGETARIAN, DBUtils.PREF_VEGAN, DBUtils.PREF_GLUTEN_FREE};
        Cursor cursor = SQLiteHandler.selectUserPreferences(InstrumentationRegistry.getTargetContext(),fields,params);
        cursor.moveToFirst();
        Assert.assertEquals(isVegetarian,cursor.getInt(0));
        Assert.assertEquals(isNotVegan,cursor.getInt(1));
        Assert.assertEquals(isGlutenFree,cursor.getInt(2));
    }
}
