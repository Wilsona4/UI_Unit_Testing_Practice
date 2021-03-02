package com.funcrib.expressouitestingpractice.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.ui.main.MainActivity.Companion.buildToastMessage
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DialogAndToastTest {
    @Before
    fun setUp(){
        //        SETUP
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    }
    @Test
    fun testShowDialog_captureInput() {

        val name = "WILSON"
//        EXECUTE AND VERIFY
//        check if dialog is launch after button click
        onView(withId(R.id.button_launch_dialog)).perform(click())
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

//        check if ok button is clickable without input
        onView(withText(R.string.text_ok)).perform(click())
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

//        enter a name
        onView(withId(R.id.md_input_message)).perform(typeText(name))

//        check if the ok button is now working and the dialog is gone
        onView(withText(R.string.text_ok)).perform(click())
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

//        check in textView text matches input in the dialog
        onView(withId(R.id.text_name)).check(matches(withText(name)))

    }

    @Test
    fun testToastDisplayed_toastMessage() {
        val name = "WILSON"
//        NB:   You have to run the entire test class access this test
//        check if the toast displayed and the is message correct
        onView(withText(buildToastMessage(name))).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

    }
}