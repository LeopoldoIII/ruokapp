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

public class DiscoverActivityTest {

    @Rule
    public ActivityTestRule<DiscoverActivity> mActivityTestRule = new ActivityTestRule<>(DiscoverActivity.class);

    @Test
    public void verifyDiscoverActivity(){
        Espresso.onView(withId(R.id.image_reference)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.food_name)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.icon_difficult)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.icon_timer)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.time)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.icon_not_like)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.icon_like)).check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(withId(R.id.icon_not_like)).check(ViewAssertions.matches(isClickable()));
        Espresso.onView(withId(R.id.icon_like)).check(ViewAssertions.matches(isClickable()));
    }
}
