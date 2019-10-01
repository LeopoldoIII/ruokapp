package com.ruokapp.views;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<SignUpActivity> mActivityTestRule = new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void verifyRegisterActivity(){
        Espresso.onView(withId(R.id.logo_ruokapp)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_app)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_user)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.input_user)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_email)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.input_email)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_password)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.input_password)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.input_password2)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.btn_sign_up)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.link_login)).check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(withId(R.id.input_user)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.input_email)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.input_password)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.input_password2)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.btn_sign_up)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.link_login)).check(ViewAssertions.matches(isClickable()));
    }

}
