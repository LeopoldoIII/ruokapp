package com.ruokapp.views;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ruokapp.R;
import com.ruokapp.core.user.User;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.util.Converter;

public class PreferenceActivity extends AppCompatActivity {

    Switch checkboxVegetarian;
    Switch checkboxVegan;
    Switch checkboxGlutenFree;

    boolean vegetarianOption;
    boolean veganOption;
    boolean glutenFreeOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        checkboxVegetarian =    findViewById(R.id.checkbox_vegetarian);
        checkboxVegan =         findViewById(R.id.checkbox_vegan);
        checkboxGlutenFree =    findViewById(R.id.checkbox_gluten_free);

        loadPreferenceFromUser();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            try {
                if (getIntent().getExtras().getString("from").equals("SingUp"))
                    startActivity(new Intent(this, HomeActivity.class));
            } catch (NullPointerException e) {
            }
            this.finish();
        }
        savePreferences();
        return super.onOptionsItemSelected(item);
    }

    private void loadPreferenceFromUser(){
        String[] fields = {DBUtils.PREF_VEGETARIAN, DBUtils.PREF_VEGAN, DBUtils.PREF_GLUTEN_FREE};
        String[] params = {String.format("%s",User.getInstanceUser().getId())};
        Cursor cursor = SQLiteHandler.selectUserPreferences(this,fields,params);
        cursor.moveToFirst();
        vegetarianOption = Converter.convertIntToBoolean(cursor.getInt(0));
        veganOption = Converter.convertIntToBoolean(cursor.getInt(1));
        glutenFreeOption = Converter.convertIntToBoolean(cursor.getInt(2));
        setPreferences();
    }

    private void setPreferences(){
        checkboxVegetarian.setChecked(vegetarianOption);
        checkboxVegan.setChecked(veganOption);
        checkboxGlutenFree.setChecked(glutenFreeOption);
    }

    private void savePreferences(){
        ContentValues preferences = new ContentValues();
        preferences.put(DBUtils.PREF_VEGETARIAN,Converter.convertBooleanToInt(checkboxVegetarian.isChecked()));
        preferences.put(DBUtils.PREF_VEGAN,Converter.convertBooleanToInt(checkboxVegan.isChecked()));
        preferences.put(DBUtils.PREF_GLUTEN_FREE,Converter.convertBooleanToInt(checkboxGlutenFree.isChecked()));
        String[] params = {String.format("%s",User.getInstanceUser().getId())};
        SQLiteHandler.updatePreferences(this,preferences,params);
        setUserPreference();
    }

    private void setUserPreference(){
        User.getInstanceUser().getPreference().setPrefVegetarian(checkboxVegetarian.isChecked());
        User.getInstanceUser().getPreference().setPrefVegan(checkboxVegan.isChecked());
        User.getInstanceUser().getPreference().setPrefGlutenFree(checkboxGlutenFree.isChecked());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        savePreferences();
        finish();
    }
}