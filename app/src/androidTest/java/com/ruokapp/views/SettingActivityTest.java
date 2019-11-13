package com.ruokapp.views;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import com.ruokapp.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class SettingActivityTest {

    @Rule
    public ActivityTestRule<PreferenceActivity> mActivityTestRule = new ActivityTestRule<>(PreferenceActivity.class);

    @Test
    public void verifySettingsActivity(){
        Espresso.onView(withId(R.id.card_diets)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_diet)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_vegan)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_vegetarian)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_gluten_free)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.checkbox_vegan)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.checkbox_vegetarian)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.checkbox_gluten_free)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_diet)).check(ViewAssertions.matches(withText("Diets")));
        Espresso.onView(withId(R.id.title_vegan)).check(ViewAssertions.matches(withText("Vegan")));
        Espresso.onView(withId(R.id.title_vegetarian)).check(ViewAssertions.matches(withText("Vegetarian")));
        Espresso.onView(withId(R.id.title_gluten_free)).check(ViewAssertions.matches(withText("Gluten Free")));

        Espresso.onView(withId(R.id.checkbox_vegan)).check(ViewAssertions.matches(isNotChecked()));
        Espresso.onView(withId(R.id.checkbox_vegetarian)).check(ViewAssertions.matches(isNotChecked()));
        Espresso.onView(withId(R.id.checkbox_gluten_free)).check(ViewAssertions.matches(isNotChecked()));
    }
}
