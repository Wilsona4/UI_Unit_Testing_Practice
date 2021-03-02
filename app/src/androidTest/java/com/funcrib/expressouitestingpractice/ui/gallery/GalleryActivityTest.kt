package com.funcrib.expressouitestingpractice.ui.gallery

import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.media.MediaBrowserServiceCompat.RESULT_OK
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.funcrib.expressouitestingpractice.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class GalleryActivityTest {
    @get:Rule
    val intentTestRule = IntentsTestRule(GalleryActivity::class.java)

    @Test
    fun testValidateGalleryIntent() {
//        SETUP
        val expectedIntent: Matcher<Intent> = allOf(
            hasAction(Intent.ACTION_PICK),
            hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        )

        val activityResult = createGalleryIntentResultStub()
        intending(expectedIntent).respondWith(activityResult)

//        Execute and Verify
        onView(withId(R.id.button_open_gallery)).perform(click())
        intended(expectedIntent)
    }

    private fun createGalleryIntentResultStub(): Instrumentation.ActivityResult {
//        create resource context
        val resources: Resources = InstrumentationRegistry.getInstrumentation().context.resources
//        create mock imageUri
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                    resources.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                    resources.getResourcePackageName(R.drawable.ic_launcher_background)
        )
//       build mock result to be returned
        val resultIntent = Intent()
        resultIntent.data = imageUri
        return Instrumentation.ActivityResult(RESULT_OK, resultIntent)
    }
}