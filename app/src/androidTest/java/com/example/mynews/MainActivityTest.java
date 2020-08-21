package com.example.mynews;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import controllers.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;



public class MainActivityTest {

   @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void interfaceDisplayTest() throws Exception {
        onView(withId(R.id.activity_main_drawer_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
    }
}
