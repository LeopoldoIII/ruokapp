package com.ruokapp.preferences;

import com.ruokapp.core.helper.StringPreference;
import com.ruokapp.core.user.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PreferenceTest {

    boolean isVegetarian = true;
    boolean isVegan = false;
    boolean isGlutenFree = true;

    @Before
    public void setUp(){
        User.getInstanceUser().getPreference().setPrefVegetarian(isVegetarian);
        User.getInstanceUser().getPreference().setPrefVegan(isVegan);
        User.getInstanceUser().getPreference().setPrefGlutenFree(isGlutenFree);
    }

    @Test
    public void setUserPreference(){
        Assert.assertEquals(isVegetarian, User.getInstanceUser().getPreference().isVegetarian());
        Assert.assertEquals(isVegan, User.getInstanceUser().getPreference().isVegan());
        Assert.assertEquals(isGlutenFree, User.getInstanceUser().getPreference().isGlutenFree());
    }

    @Test
    public void getUserPreferences(){
        Assert.assertEquals(StringPreference.PREF_VEGETARIAN, User.getInstanceUser().getPreference().getPreferences()[0]);
        Assert.assertEquals(null, User.getInstanceUser().getPreference().getPreferences()[1]);
        Assert.assertEquals(StringPreference.PREF_GLUTEN_FREE, User.getInstanceUser().getPreference().getPreferences()[2]);
    }

}
