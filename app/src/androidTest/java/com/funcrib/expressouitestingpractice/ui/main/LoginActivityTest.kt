package com.funcrib.expressouitestingpractice.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testActivityInView() {
        onView(withId(R.id.activity_login_title)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisibility_title_buttonTitle() {
        onView(withId(R.id.activity_login_title)).check(matches(isDisplayed()))
        onView(withId(R.id.button_login)).check(matches(isDisplayed()))
    }

    @Test
    fun testTitleTextIsDisplayed() {
        onView(withId(R.id.activity_login_title)).check(matches(withText(R.string.text_login_page)))
    }

    @Test
    fun testSpinnerIsDisplayed(){
        onView(withId(R.id.spinner)).check(matches(isDisplayed()))
    }
}