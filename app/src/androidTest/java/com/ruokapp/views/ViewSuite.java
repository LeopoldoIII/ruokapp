package com.ruokapp.views;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WelcomeActivityTest.class,
        LoginActivityTest.class,
        SingUpActivityTest.class,
        HomeActivityTest.class,
        DiscoverActivityTest.class,
        FavActivityTest.class,
        AboutUsActivityTest.class,
        FoodActivityTest.class,
        SettingActivityTest.class})

public class ViewSuite {
}
