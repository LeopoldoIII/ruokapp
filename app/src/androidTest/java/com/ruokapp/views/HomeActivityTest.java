package com.ruokapp.views;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void verifyHomeActivity(){
        Espresso.onView(withId(R.id.layout_background_explorer)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.layout_background_fav)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.discover_image)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.buttonExplorer)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.background_fav)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.icon_fav)).check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(withId(R.id.layout_background_explorer)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.layout_background_fav)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.buttonExplorer)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.discover_image)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.background_fav)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.icon_fav)).check(ViewAssertions.matches(isClickable()));

    }


}
