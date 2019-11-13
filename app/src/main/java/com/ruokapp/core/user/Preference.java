package com.ruokapp.core.user;

import com.ruokapp.core.helper.StringPreference;

public class Preference {

    private boolean pref_vegan;
    private boolean pref_vegetarian;
    private boolean pref_gluten_free;
    private String[] preferences = new String[StringPreference.PREFERENCES_COUNT];

    public boolean hasPreferences(){
        return pref_vegetarian || pref_vegan || pref_gluten_free;
    }

    public boolean isVegan() { return pref_vegan; }

    public void setPrefVegan(boolean pref_vegan) { this.pref_vegan = pref_vegan; }

    public boolean isVegetarian() { return pref_vegetarian; }

    public void setPrefVegetarian(boolean pref_vegetarian) { this.pref_vegetarian = pref_vegetarian; }

    public boolean isGlutenFree() { return pref_gluten_free; }

    public void setPrefGlutenFree(boolean pref_gluten_free) { this.pref_gluten_free = pref_gluten_free; }

    public String[] getPreferences(){
        if (pref_vegetarian){
            preferences[0] = StringPreference.PREF_VEGETARIAN;
        } else{
            preferences[0] = null;
        }
        if (pref_vegan){
            preferences[1] = StringPreference.PREF_VEGAN;
        } else {
            preferences[1] = null;
        }
        if (pref_gluten_free){
            preferences[2] = StringPreference.PREF_GLUTEN_FREE;
        } else {
            preferences[2] = null;
        }
        return preferences;
    }


}
