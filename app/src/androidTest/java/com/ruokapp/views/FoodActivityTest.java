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

public class FoodActivityTest {

    @Rule
    public ActivityTestRule<FoodActivity> mActivityTestRule = new ActivityTestRule<>(FoodActivity.class);

    @Test
    public void verifyFoodActivity(){
        Espresso.onView(withId(R.id.main_image_food)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.main_title_food)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.icon_difficult)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.icon_time)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.time)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_ingredients)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.title_preparation)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.ingredients)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.preparation_steps)).check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(withId(R.id.title_ingredients)).check(ViewAssertions.matches(withText("Ingredientes")));
        Espresso.onView(withId(R.id.title_preparation)).check(ViewAssertions.matches(withText("Preparacion")));
    }
}
