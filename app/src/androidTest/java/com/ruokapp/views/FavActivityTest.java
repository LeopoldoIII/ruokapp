package com.ruokapp.views;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class FavActivityTest {

    @Rule
    public ActivityTestRule<FavActivity> mActivityTestRule = new ActivityTestRule<>(FavActivity.class);

    @Test
    public void verifyFavActivity(){
        Espresso.onView(withId(R.id.icon_fav)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_matches)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.food_matches)).check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(withId(R.id.title_matches)).check(ViewAssertions.matches(withText("Matches")));
    }
}
