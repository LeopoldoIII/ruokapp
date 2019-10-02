package com.ruokapp.views;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WelcomeActivityTest.class,
        LoginActivityTest.class,
        RegisterActivityTest.class,
        HomeActivityTest.class,
        DiscoverActivityTest.class,
        FavActivityTest.class,
        AboutUsActivityTest.class,
        FoodActivityTest.class})

public class ViewSuite {
}
