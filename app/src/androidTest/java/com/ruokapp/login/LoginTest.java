package com.ruokapp.login;

import android.content.ContentValues;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;
import com.ruokapp.core.Session;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.views.WelcomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Before
    public void startUp() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Test
    public void verifyLoginFromExistingUser() {
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
        Espresso.onView(withId(R.id.buttonExplorer)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.background_fav)).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void verifyLoginFromUnregisteredUser() {
        // Data
        String email = "unregistered@test.com";
        String password = "pass.1234";

        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.input_email)).check(ViewAssertions.matches(isDisplayed()));

    }

}
