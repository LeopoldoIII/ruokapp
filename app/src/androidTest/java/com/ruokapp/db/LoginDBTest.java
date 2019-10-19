package com.ruokapp.db;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.test.InstrumentationRegistry;

import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LoginDBTest {


    @Test
    public void verifyLoginFromExistingUser() {
        //data
        String name = "UserRegistered";
        String email = String.format("existing+%s@test.com", new Date().getTime());
        String pass = "pass.1234";

        // Pre-condition
        ContentValues data = new ContentValues();
        data.put(DBUtils.USER_NAME, name);
        data.put(DBUtils.USER_EMAIL, email);
        data.put(DBUtils.USER_PASSWORD, pass);
        long id = SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(), data);

        String[] fields = {DBUtils.ID_USER, DBUtils.USER_EMAIL, DBUtils.USER_NAME, DBUtils.USER_PASSWORD};
        String[] params = {email, pass};
        Cursor cursor = SQLiteHandler.searchUserFromLogin(InstrumentationRegistry.getTargetContext(), fields, params);
        cursor.moveToFirst();
        Assert.assertEquals(id, cursor.getLong(0));
        Assert.assertEquals(email, cursor.getString(1));
        Assert.assertEquals(name, cursor.getString(2));
        Assert.assertEquals(pass, cursor.getString(3));
    }

    @Test
    public void verifyLoginFromNotExistingUser() {
        //data
        String email = "unregistered@mail.test";
        String pass = "pass.1234";

        String[] fields = {DBUtils.ID_USER, DBUtils.USER_EMAIL, DBUtils.USER_NAME, DBUtils.USER_PASSWORD};
        String[] params = {email, pass};
        Cursor cursor = SQLiteHandler.searchUserFromLogin(InstrumentationRegistry.getTargetContext(), fields, params);
        Assert.assertEquals(0, cursor.getCount());
        Assert.assertFalse(cursor.moveToFirst());
    }

}