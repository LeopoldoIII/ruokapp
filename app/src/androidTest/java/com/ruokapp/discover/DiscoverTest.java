package com.ruokapp.discover;

import android.content.ContentValues;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;
import com.ruokapp.core.recipe.Recipe;
import com.ruokapp.core.session.Session;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.views.WelcomeActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DiscoverTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @BeforeClass
    public static void startSuite(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Before
    public void startUp(){
        // Data
        String username = "UserRegistered";
        String email = String.format("registered+%s@test.com", new Date().getTime());
        String password = "pass.1234";

        // Pre-Condition
        ContentValues data = new ContentValues();
        data.put(DBUtils.USER_NAME, username);
        data.put(DBUtils.USER_EMAIL, email);
        data.put(DBUtils.USER_PASSWORD, password);
        SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(), data);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.buttonExplorer)).perform(ViewActions.click());
    }

    @Test
    public void userLikeFood(){
        int idFoodA = Recipe.getInstance().getId();
        Espresso.onView(withId(R.id.icon_like)).perform(ViewActions.click());
        int idFoodB = Recipe.getInstance().getId();
        Assert.assertNotEquals(idFoodA,idFoodB);
    }

    @Test
    public void userUnLikeFood(){
        int idFoodA = Recipe.getInstance().getId();
        Espresso.onView(withId(R.id.icon_not_like)).perform(ViewActions.click());
        int idFoodB = Recipe.getInstance().getId();
        Assert.assertNotEquals(idFoodA,idFoodB);
    }

    @Before
    public void tearDown(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }
}
