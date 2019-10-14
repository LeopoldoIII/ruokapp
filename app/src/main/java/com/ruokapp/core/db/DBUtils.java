package com.ruokapp.core.db;

public class DBUtils {

    public static final String DB_NAME = "ruokapp_db";
    public static final int DB_VERSION = 201910;

    public static final String USER_TABLE = "users";

    public static final String ID_USER = "id_user";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String PREF_VEGAN = "pref_vegan";
    public static final String PREF_VEGETARIAN = "pref_vegetarian";
    public static final String PREF_GLUTEN_FREE = "pref_gluten_free";
    public static final String PREF_CHINESE = "pref_chinese";
    public static final String PREF_ITALIAN = "pref_italian";
    public static final String PREF_JAPANESE = "pref_japanese";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE "+USER_TABLE+" (" +
            ID_USER+" INTEGER PRIMARY KEY, "+
            USER_NAME+" TEXT NOT NULL, "+
            USER_EMAIL+" TEXT NOT NULL, "+
            USER_PASSWORD+" TEXT NOT NULL, "+
            PREF_VEGAN+" INTEGER, "+
            PREF_VEGETARIAN+" INTEGER, "+
            PREF_GLUTEN_FREE+" INTEGER, "+
            PREF_CHINESE+" INTEGER, "+
            PREF_ITALIAN+" INTEGER, "+
            PREF_JAPANESE+" INTEGER)";

    public static final String FOOD_REF_TABLE = "food_ref";

    public static final String ID_FOOD_REF_TABLE = "id";
    public static final String ID_FOOD_REF = "id_food_ref";

    public static final String CREATE_TABLE_FOOD_REF = "CREATE TABLE "+FOOD_REF_TABLE+" ("+
            ID_FOOD_REF_TABLE+" INTEGER PRIMARY KEY, "+
            ID_FOOD_REF+" INTEGER NOT NULL, "+
            ID_USER+" INTEGER,"+
            "FOREIGN KEY ("+ID_USER+") REFERENCES "+USER_TABLE+"("+ID_USER+"))";

    public static final String SELECT_TABLES_FROM_DATABASE = "SELECT name FROM sqlite_master WHERE type='table' AND name!='android_metadata'";

}
