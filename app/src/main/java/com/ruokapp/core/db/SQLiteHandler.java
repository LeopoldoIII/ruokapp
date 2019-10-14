package com.ruokapp.core.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SQLiteHandler {

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

    public static ConnectionSQLiteHelper createConnection(Context context){
        try {
            return new ConnectionSQLiteHelper(context, DBUtils.DB_NAME,null, DBUtils.DB_VERSION);
        } catch (Exception e){
        }
        return null;
    }

}
