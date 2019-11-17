package com.ruokapp.favorites;

import android.content.ContentValues;
import android.view.View;
import android.widget.TextView;

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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;


public class FavoritesUITest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @BeforeClass
    public static void startUp(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Before
    public void setUp(){
        waitForWelcomeActivity();
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

        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
    }

    @After
    public void tearDown(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Test
    public void userAddFavoriteFoodAndCheckInFavMenu(){

        Espresso.onView(withId(R.id.buttonExplorer)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.icon_like)).perform(ViewActions.click());

        Espresso.pressBack();
        Espresso.onView(withId(R.id.icon_fav)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.image_food)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_food)).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void userWithoutLikedFoodSeeEmptyList(){
        Espresso.onView(withId(R.id.icon_fav)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.image_food)).check(doesNotExist());
        Espresso.onView(withId(R.id.title_food)).check(doesNotExist());
    }

    private void waitForWelcomeActivity(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
