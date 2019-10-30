package com.ruokapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.InstrumentationRegistry;

import com.ruokapp.core.db.ConnectionSQLiteHelper;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DBTest {

    private static String dbName = "dbTest";
    private static int dbVersion = 101;
    private int sizeDB = 2;

    private String emailTest = "davidsghz@gmail.com";
    private String userNameTest = "David Gonzalez";
    private String userPassTest = "test.1234";
    private long idUser;

    private static ConnectionSQLiteHelper connection =  new ConnectionSQLiteHelper(InstrumentationRegistry.getTargetContext(), dbName,null, dbVersion);
    private static SQLiteDatabase sqLiteDatabase = connection.getWritableDatabase();

    @Test
    public void verifyCreationDB(){
        Assert.assertEquals("The name of the DB is different.",dbName,connection.getDatabaseName());
        Assert.assertEquals("The version number is different.",dbVersion,sqLiteDatabase.getVersion());

        Cursor cursor = sqLiteDatabase.rawQuery(DBUtils.SELECT_TABLES_FROM_DATABASE,null);
        ArrayList<String> tablesName = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                tablesName.add( cursor.getString( cursor.getColumnIndex("name")) );
                cursor.moveToNext();
            }
        }

        Assert.assertEquals("The size of the DB is different",sizeDB,tablesName.size());
        Assert.assertEquals("The name of the table 'Users' is different",DBUtils.USER_TABLE,tablesName.get(0));
        Assert.assertEquals("The name of the table 'Food_Ref' is different",DBUtils.FOOD_REF_TABLE,tablesName.get(1));
    }

    @Test
    public void verifyInsertUserData(){
        //Pre-condition
        verifyCreationDB();

        ContentValues data = new ContentValues();

        data.put(DBUtils.USER_NAME, userNameTest);
        data.put(DBUtils.USER_EMAIL, emailTest);
        data.put(DBUtils.USER_PASSWORD, userPassTest);

        idUser = sqLiteDatabase.insert(DBUtils.USER_TABLE,DBUtils.ID_USER,data);
        Assert.assertTrue(idUser!=0);
    }

    @Test
    public void verifySelectUserData(){
        //Pre-condition
        verifyInsertUserData();

        SQLiteDatabase sqLiteDatabaseRead = connection.getReadableDatabase();
        String[] params = {Long.toString(idUser)};
        String[] fields = {DBUtils.ID_USER,DBUtils.USER_EMAIL, DBUtils.USER_NAME, DBUtils.USER_PASSWORD};
        Cursor cursor = sqLiteDatabaseRead.query(DBUtils.USER_TABLE,fields,DBUtils.ID_USER+"=?",params,null,null,null);
        cursor.moveToFirst();
        Assert.assertEquals("The Columns count is different",cursor.getColumnCount(), fields.length);
        Assert.assertEquals("The user ID is different",Long.toString(idUser),cursor.getString(0));
        Assert.assertEquals("The email info is different",emailTest,cursor.getString(1));
        Assert.assertEquals("The user name is different",userNameTest,cursor.getString(2));
        Assert.assertEquals("The user password is different",userPassTest,cursor.getString(3));
    }

    @Test
    public void verifyInsertFoodRef(){
        //Pre-condition
        verifyCreationDB();

        ContentValues data = new ContentValues();
        long result = 0;
        data.put(DBUtils.ID_USER, idUser);
        data.put(DBUtils.ID_FOOD_REF, 1234);

        result = sqLiteDatabase.insert(DBUtils.FOOD_REF_TABLE,DBUtils.ID_FOOD_REF_TABLE,data);
        System.out.println(String.format("The result was: %s",result));
        Assert.assertTrue(result!=0);
    }

    @Test
    public void verifySelectFoodRef(){
        //Pre-condition
        verifyInsertFoodRef();

        SQLiteDatabase sqLiteDatabaseRead = connection.getReadableDatabase();
        String[] params = {Long.toString(idUser)};
        String[] fields = {DBUtils.ID_FOOD_REF_TABLE,DBUtils.ID_USER, DBUtils.ID_FOOD_REF};
        Cursor cursor = sqLiteDatabaseRead.query(DBUtils.FOOD_REF_TABLE,fields,DBUtils.ID_USER+"=?",params,null,null,null);
        cursor.moveToFirst();
        Assert.assertEquals("The Columns count is different",cursor.getColumnCount(), fields.length);
        Assert.assertTrue("The main ID is 0",!cursor.getString(0).equals(0));
        Assert.assertEquals("The user ID is different",Long.toString(idUser),cursor.getString(1));
        Assert.assertEquals("The food reference is different","1234",cursor.getString(2));
    }

}
