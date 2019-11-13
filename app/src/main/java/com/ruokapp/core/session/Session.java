package com.ruokapp.core.session;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class Session {

    private static SharedPreferences preferences;
    private static final String USER_EMAIL = "userEmail";
    private static final String USER_PASS = "userPass";
    private static Session session = null;

    public static Session getInstance(Context context){
        if(session == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            session = new Session();
        }
        return session;
    }

    public void createSession(String email, String password){
        preferences.edit().putString(USER_EMAIL,email).commit();
        preferences.edit().putString(USER_PASS,password).commit();
    }

    /**
     * return an Array with Email and Password from the user, if not session active
     * return null
     * @return
     */
    public String[] getUserSession(){
        String[] data = null;
        if((preferences.getString(USER_EMAIL,null)!=null) &&
                preferences.getString(USER_PASS,null)!= null){
            data = new String[2];
            data[0] = preferences.getString(USER_EMAIL,null);
            data[1] = preferences.getString(USER_PASS,null);
        }
        return data;
    }

    public void closeSession(){
        preferences.edit().putString(USER_EMAIL,null).commit();
        preferences.edit().putString(USER_PASS,null).commit();
        session = null;
    }

    @Deprecated
    public static Session getSession(){
        return session;
    }

}
