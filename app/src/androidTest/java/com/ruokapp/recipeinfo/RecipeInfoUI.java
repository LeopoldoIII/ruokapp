package com.ruokapp.recipeinfo;

import android.content.ContentValues;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.session.Session;
import com.ruokapp.views.WelcomeActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsAnything.anything;

public class RecipeInfoUI {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @BeforeClass
    public static void setUpSuite(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Before
    public void setUp(){
        waitForWelcomeActivity();
        String username = "UserRegistered";
        String email = String.format("registered+%s@test.com", new Date().getTime());
        String password = "pass.1234";

        // Pre-Condition
        ContentValues data = new ContentValues();
        data.put(DBUtils.USER_NAME, username);
        data.put(DBUtils.USER_EMAIL, email);
        data.put(DBUtils.USER_PASSWORD, password);
        SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(), data);

        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(click());
        Espresso.onView(withId(R.id.buttonExplorer)).perform(click());
        Espresso.onView(withId(R.id.icon_like)).perform(click());
        Espresso.pressBack();
    }

    @Test
    @Ignore("Test case ignored for unstable Espresso performance to do click on an item of the list")
    public void userSeeRecipeInfo(){
        Espresso.onView(withId(R.id.icon_fav)).perform(click());
        Espresso.onData(anything()).inAdapterView(allOf(withId(R.id.food_matches))).atPosition(0).perform(ViewActions.click());
        Espresso.onView(withId(R.id.main_image_food)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.main_title_food)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.time)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.ingredients)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.preparation_steps)).check(ViewAssertions.matches(isDisplayed()));
    }

    private void waitForWelcomeActivity(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
