package com.ruokapp.core.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SQLiteHandler {

    public static ConnectionSQLiteHelper createConnection(Context context){
        try {
            return new ConnectionSQLiteHelper(context, DBUtils.DB_NAME,null, DBUtils.DB_VERSION);
        } catch (Exception e){
        }
        return null;
    }

    public static long insertUser(Context context, ContentValues data){
        try {
            return createConnection(context)
                    .getWritableDatabase()
                    .insert(DBUtils.USER_TABLE,DBUtils.ID_USER,data);
        } catch (Exception e){
            return -1;
        }
    }

    public static Cursor searchUserByEmail(Context context, String[] fields, String[] params){
        try {
            return createConnection(context)
                    .getReadableDatabase()
                    .query(DBUtils.USER_TABLE,fields,DBUtils.USER_EMAIL+"=?",params,
                            null,null,null);
        } catch (Exception e){
            return null;
        }
    }

    public static Cursor searchUserFromLogin(Context context, String[] fields, String[] params){
        try {
            return createConnection(context)
                    .getReadableDatabase()
                    .query(DBUtils.USER_TABLE,fields
                            ,String.format("%s=? AND %s=?",
                                    DBUtils.USER_EMAIL,DBUtils.USER_PASSWORD)
                            ,params,null,null,null);
        } catch (Exception e){
            return null;
        }
    }

    public static long insertFoodRef(Context context, ContentValues data){
        try {
            return createConnection(context)
                    .getWritableDatabase()
                    .insert(DBUtils.FOOD_REF_TABLE,DBUtils.ID_FOOD_REF_TABLE,data);
        } catch (Exception e){
            return -1;
        }
    }

    public static Cursor selectUserPreferences(Context context,String[] fields, String[] params){
        try {
            return createConnection(context)
                    .getReadableDatabase()
                    .query(DBUtils.USER_TABLE,fields
                            ,String.format("%s=?",
                                    DBUtils.ID_USER)
                            ,params,null,null,null);
        } catch (Exception e){
            return null;
        }
    }

    public static int updatePreferences(Context context, ContentValues data, String[] params){
        try{
            return createConnection(context)
                    .getWritableDatabase()
                    .update(DBUtils.USER_TABLE, data,DBUtils.ID_USER+"=?",params);
        } catch (Exception e){
            return -1;
        }
    }

    public static Cursor getRecipesFromUser(Context context, String[] fields, String[] params){
        try {
            return createConnection(context)
                    .getReadableDatabase()
                    .query(DBUtils.FOOD_REF_TABLE,fields,DBUtils.ID_USER+"=?",params,
                            null,null,null);
        } catch (Exception e){
            return null;
        }
    }

}
