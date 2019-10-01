package com.ruokapp.views;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.ruokapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void verifyLoginActivity() {

        ViewInteraction imageView = onView(
                allOf(withId(R.id.logo_ruokapp), withContentDescription("Ruokapp"),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.title_email), withText("Email"),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.title_email), withText("Email"),
                        isDisplayed()));
        textView2.check(matches(withText("Email")));

        ViewInteraction inputMail = onView(
                allOf(withId(R.id.input_email),isDisplayed()));
        inputMail.check(matches(isDisplayed()));
        inputMail.check(matches(isClickable()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.input_password),isDisplayed()));
        editText2.check(matches(isDisplayed()));
        editText2.check(matches(isClickable()));

        ViewInteraction button = onView(
                allOf(withId(R.id.btn_login),isDisplayed()));
        button.check(matches(isDisplayed()));
        button.check(matches(isClickable()));


        ViewInteraction textView3 = onView(
                allOf(withId(R.id.link_register), withText("Not account yet? Create one"),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));
        textView3.check(matches(isClickable()));
    }

}
