package com.ruokapp.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionSQLiteHelper extends SQLiteOpenHelper {

    public ConnectionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBUtils.CREATE_TABLE_USERS);
        db.execSQL(DBUtils.CREATE_TABLE_FOOD_REF);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DBUtils.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+DBUtils.FOOD_REF_TABLE);
        onCreate(db);
    }

}
