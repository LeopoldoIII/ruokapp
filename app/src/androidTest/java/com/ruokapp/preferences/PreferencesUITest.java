package com.ruokapp.preferences;

import android.content.ContentValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.core.session.Session;
import com.ruokapp.core.user.User;
import com.ruokapp.views.WelcomeActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class PreferencesUITest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @BeforeClass
    public static void setUpSuite(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Before
    public void setUp(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
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
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
        openMenu();
        clickPreferences();
        Espresso.onView(withId(R.id.title_diet)).check(ViewAssertions.matches(isDisplayed()));
    }


    @Test
    public void userSetPreferences(){
        Espresso.onView(withId(R.id.checkbox_vegetarian)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.checkbox_gluten_free)).perform(ViewActions.click());
        Espresso.pressBack();
        openMenu();
        clickPreferences();
        Espresso.onView(withId(R.id.checkbox_vegetarian)).check(ViewAssertions.matches(isChecked()));
        Espresso.onView(withId(R.id.checkbox_vegan)).check(ViewAssertions.matches(isNotChecked()));
        Espresso.onView(withId(R.id.checkbox_gluten_free)).check(ViewAssertions.matches(isChecked()));
    }

    @Test
    public void userModifyPreferences(){
        Espresso.onView(withId(R.id.checkbox_vegetarian)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.checkbox_gluten_free)).perform(ViewActions.click());
        Espresso.pressBack();
        openMenu();
        clickPreferences();
        Espresso.onView(withId(R.id.checkbox_vegetarian)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.checkbox_vegan)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.checkbox_gluten_free)).perform(ViewActions.click());
        Espresso.pressBack();
        openMenu();
        clickPreferences();
        Espresso.onView(withId(R.id.checkbox_vegetarian)).check(ViewAssertions.matches(isNotChecked()));
        Espresso.onView(withId(R.id.checkbox_vegan)).check(ViewAssertions.matches(isChecked()));
        Espresso.onView(withId(R.id.checkbox_gluten_free)).check(ViewAssertions.matches(isNotChecked()));

    }

    private void waitForWelcomeActivity(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void openMenu(){
        Espresso.onView(allOf(
                withContentDescription("More options"),
                childAtPosition(childAtPosition(
                        withId(R.id.action_bar),1),0)))
                .perform(ViewActions.click());
    }

    private void clickPreferences(){
        Espresso.onView(allOf(
                withId(R.id.title), withText("Preferences"),
                childAtPosition(childAtPosition(
                        withId(R.id.content),0),0)))
                .perform(ViewActions.click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
