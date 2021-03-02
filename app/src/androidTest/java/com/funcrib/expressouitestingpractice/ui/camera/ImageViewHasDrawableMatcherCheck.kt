package com.funcrib.expressouitestingpractice.ui.camera

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

object ImageViewHasDrawableMatcherCheck {

    fun hasDrawable(): BoundedMatcher<View, ImageView> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("Drawable Detected")
            }

            override fun matchesSafely(item: ImageView?): Boolean {
                return item?.drawable != null
            }

        }
    }
}