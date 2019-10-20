package com.ruokapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.test.InstrumentationRegistry;

import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SingUpDBTest {

    ContentValues user = new ContentValues();

    @Test
    public void insertUserToDB(){
        String userName = "NewUser";
        String email = String.format("singUp+%s@test.com",new Date().getTime());
        String pass = "passTest";
        user.put(DBUtils.USER_NAME,userName );
        user.put(DBUtils.USER_EMAIL, email);
        user.put(DBUtils.USER_PASSWORD, pass);
        long id = SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(),user);
        Assert.assertTrue("Error: The user ID is 0",id!=0);
    }

    @Test
    public void searchExistingEmailUser(){
        String userName = "ExistingUser";
        String email = String.format("existing+%s@test.com",new Date().getTime());
        String pass = "passTest";
        user.put(DBUtils.USER_NAME,userName );
        user.put(DBUtils.USER_EMAIL, email);
        user.put(DBUtils.USER_PASSWORD, pass);
        Context context = InstrumentationRegistry.getTargetContext();
        long id = SQLiteHandler.insertUser(context,user);

        String[] fields = {DBUtils.ID_USER,DBUtils.USER_NAME, DBUtils.USER_EMAIL, DBUtils.USER_PASSWORD};
        String[] params = {email};
        Cursor cursor = SQLiteHandler.searchUserByEmail(context,fields,params);
        cursor.moveToFirst();
        Assert.assertEquals("The ID User is different",id,cursor.getLong(0));
        Assert.assertEquals("The UserName is different",userName,cursor.getString(1));
        Assert.assertEquals("The UserEmail is different",email,cursor.getString(2));
        Assert.assertEquals("The UserPass is different",pass,cursor.getString(3));
    }

}
