package com.ruokapp.session;

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
import com.ruokapp.core.session.Session;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.views.WelcomeActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class SessionUITest {

    @Rule
    public ActivityTestRule<WelcomeActivity> activityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @BeforeClass
    public static void setUpSuite(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @AfterClass
    public static void tearDownSuite(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Before
    public void setUp(){
        Session.getInstance(InstrumentationRegistry.getTargetContext()).closeSession();
    }

    @Test
    public void userHoldSessionAfterRegister(){
        String username = "NewUser";
        String email = String.format("%s@test.com",new Date().getTime());
        String password = "pass.1234";

        goToSignUp();
        Espresso.onView(withId(R.id.input_user)).perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password2)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_sign_up)).perform(ViewActions.click());
        Espresso.pressBackUnconditionally();
        activityTestRule.launchActivity(null);
        waitForWelcomeActivity();
        Espresso.onView(withId(R.id.buttonExplorer)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void userHoldSessionAfterLogin(){
        String username = "RegisteredUser";
        String email = String.format("%s@test.com",new Date().getTime());
        String password = "pass.1234";

        insertUser(username,email,password);
        waitForWelcomeActivity();

        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
        Espresso.pressBackUnconditionally();
        activityTestRule.launchActivity(null);
        waitForWelcomeActivity();
        Espresso.onView(withId(R.id.buttonExplorer)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void userCloseSession(){
        String username = "RegisteredUser";
        String email = String.format("%s@test.com",new Date().getTime());
        String password = "pass.1234";

        insertUser(username,email,password);
        waitForWelcomeActivity();

        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
        openMenu();
        clickLogout();
        Espresso.onView(withId(R.id.input_email)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void afterCloseSessionAndRestartAppMustBeShowLogin(){
        String username = "RegisteredUser";
        String email = String.format("%s@test.com",new Date().getTime());
        String password = "pass.1234";

        insertUser(username,email,password);
        waitForWelcomeActivity();

        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(ViewActions.click());
        openMenu();
        clickLogout();
        Espresso.pressBackUnconditionally();
        activityTestRule.launchActivity(null);
        waitForWelcomeActivity();
        Espresso.onView(withId(R.id.input_email)).check(ViewAssertions.matches(isDisplayed()));

    }

    private void goToSignUp(){
        waitForWelcomeActivity();
        Espresso.onView(withId(R.id.link_register)).perform(ViewActions.click());
    }

    private void waitForWelcomeActivity(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insertUser(String username, String email, String password){
        ContentValues data = new ContentValues();
        data.put(DBUtils.USER_NAME, username);
        data.put(DBUtils.USER_EMAIL, email);
        data.put(DBUtils.USER_PASSWORD, password);
        SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(), data);
    }

    private void openMenu(){
        Espresso.onView(allOf(
                withContentDescription("More options"),
                childAtPosition(childAtPosition(
                        withId(R.id.action_bar),1),0)))
                .perform(ViewActions.click());
    }

    private void clickLogout(){
        Espresso.onView(allOf(
                withId(R.id.title), withText("Logout"),
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
