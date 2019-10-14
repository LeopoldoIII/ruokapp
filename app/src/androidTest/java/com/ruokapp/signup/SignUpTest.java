package com.ruokapp.signup;

import android.content.ContentValues;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;
import com.ruokapp.core.db.DBUtils;
import com.ruokapp.core.db.SQLiteHandler;
import com.ruokapp.views.WelcomeActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class SignUpTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Before
    public void startUp(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.link_register), withText("Not account yet? Create one"),
                        isDisplayed()));
        appCompatTextView.perform(click());
    }

    @Test
    public void userCanRegister(){
        String username = "NewUser";
        String email = String.format("test_email+%s@test.email.com",new Date().getTime());
        String password = "pass.1234";

        Espresso.onView(withId(R.id.input_user)).perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password2)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_sign_up)).perform(ViewActions.click());
        Espresso.onView(withText("Settings")).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_diet)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_cuisines)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void userRegistered(){
        String username = "UserRegistered";
        String email = String.format("test_email+%s@test.email.com",new Date().getTime());
        String password = "pass.1234";

        //Pre-Condition
        Assert.assertTrue("Precondition failed: Could not insert the user into DB"
                ,insertUser(username,email,password)!=-1);

        Espresso.onView(withId(R.id.input_user)).perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password2)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_sign_up)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.btn_sign_up)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void userWithInvalidUsername(){
        String username = "Invalid!User#Name";
        String email = String.format("test_email+%s@test.email.com",new Date().getTime());
        String password = "pass.1234";

        Espresso.onView(withId(R.id.input_user)).perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password2)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_sign_up)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.btn_sign_up)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void userWithInvalidMail(){
        String username = "InvalidEmail";
        String email = String.format("test_email+%s#test.email.com",new Date().getTime());
        String password = "pass.1234";

        Espresso.onView(withId(R.id.input_user)).perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password2)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_sign_up)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.btn_sign_up)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void userWithInvalidPass(){
        String username = "InvalidPass";
        String email = String.format("test_email+%s@test.email.com",new Date().getTime());
        String password = "pass";

        Espresso.onView(withId(R.id.input_user)).perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password2)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_sign_up)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.btn_sign_up)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void userWithInvalidConfirmPass(){
        String username = "InvalidConfirmPass";
        String email = String.format("test_email+%s@test.email.com",new Date().getTime());
        String password = "pass.1234";
        String confirmpass = "pass1234";

        Espresso.onView(withId(R.id.input_user)).perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.input_email)).perform(ViewActions.typeText(email));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.input_password2)).perform(ViewActions.typeText(confirmpass));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_sign_up)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.btn_sign_up)).check(ViewAssertions.matches(isDisplayed()));
    }

    private long insertUser(String username, String email, String pass){
        ContentValues user = new ContentValues();
        user.put(DBUtils.USER_NAME, username);
        user.put(DBUtils.USER_EMAIL, email);
        user.put(DBUtils.USER_PASSWORD, pass);
        return SQLiteHandler.insertUser(InstrumentationRegistry.getTargetContext(),user);
    }

}
