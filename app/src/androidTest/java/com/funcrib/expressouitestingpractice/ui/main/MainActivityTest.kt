package com.funcrib.expressouitestingpractice.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.funcrib.expressouitestingpractice.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisibility_title_buttonTitle() {
        onView(withId(R.id.activity_main_title)).check(matches(isDisplayed()))
        onView(withId(R.id.button_register_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun testTitleTextIsDisplayed() {
        onView(withId(R.id.activity_main_title)).check(matches(withText(R.string.text_mainactivity)))
    }

    //    Test Navigation
    @Test
    fun testNavigationToSecondaryActivity() {
        onView(withId(R.id.button_register_activity)).perform(click())
        onView(withId(R.id.activity_login_title)).check(matches(isDisplayed()))
    }

    @Test
    fun testBackPressedToMainActivity() {
        onView(withId(R.id.button_register_activity)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

}