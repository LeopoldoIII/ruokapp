package com.ruokapp.views;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AboutUsActivityTest {

    @Rule
    public ActivityTestRule<AboutUsActivity> mActivityTestRule = new ActivityTestRule<>(AboutUsActivity.class);

    @Test
    public void verifyAboutUsActivity(){
        Espresso.onView(withId(R.id.icon_ruokapp)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.info)).check(ViewAssertions.matches(isDisplayed()));
    }
}
