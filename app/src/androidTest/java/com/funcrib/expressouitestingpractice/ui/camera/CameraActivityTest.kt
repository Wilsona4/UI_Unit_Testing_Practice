package com.funcrib.expressouitestingpractice.ui.camera

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.ui.camera.ImageViewHasDrawableMatcherCheck.hasDrawable
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CameraActivityTest {
    @get:Rule
    val intentsTestRule = IntentsTestRule(CameraActivity::class.java)

    @Test
    fun testCameraIntent_isBitmapSetToImageView() {
//        SETUP
        val expectedIntent: Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        val activityResult = createCameraIntentStub()
        intending(expectedIntent).respondWith(activityResult)

//        EXECUTE AND VERIFY
        onView(withId(R.id.image_camera)).check(matches(not(hasDrawable())))
        onView(withId(R.id.button_launch_camera)).perform(click())
        intended(expectedIntent)
        onView(withId(R.id.image_camera)).check(matches(hasDrawable()))

    }

    private fun createCameraIntentStub(): Instrumentation.ActivityResult {
//        create mock data to be parsed into intent
        val bundle = Bundle()
        bundle.putParcelable(
            KEY_IMAGE_DATA,
            BitmapFactory.decodeResource(
                intentsTestRule.activity.resources,
                R.drawable.ic_launcher_foreground
            )
        )
// create mock resultData
        val resultData = Intent()
        resultData.putExtras(bundle)
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }

}