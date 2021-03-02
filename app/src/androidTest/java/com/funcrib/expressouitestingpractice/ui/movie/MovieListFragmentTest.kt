package com.funcrib.expressouitestingpractice.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.data.DummyList
import com.funcrib.expressouitestingpractice.util.ExpressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.countingIdlingResource)
    }

    val listItemNo = 3
    val testMovie = DummyList.movies[listItemNo]

    @Test
    fun testMoviesListRecyclerViewHomePageIsDisplayed() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavToDetailFragment() {
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.scrollToPosition<MovieListAdapter.MovieListViewHolder>(
                listItemNo
            )
        ).perform(
            actionOnItemAtPosition<MovieListAdapter.MovieListViewHolder>(
                listItemNo,
                click()
            )
        )
//        verify
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavBackToMovieListRecyclerView() {
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.scrollToPosition<MovieListAdapter.MovieListViewHolder>(
                listItemNo
            )
        ).perform(
            actionOnItemAtPosition<MovieListAdapter.MovieListViewHolder>(
                listItemNo,
                click()
            )
        )
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))
        pressBack()
        //  verify
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavToDirectorsFragment_confirmDirectorsList() {
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<MovieListAdapter.MovieListViewHolder>(
                listItemNo,
                click()
            )
        )
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_directors)).perform(closeSoftKeyboard()).perform(click())

//        Verify
        onView(withId(R.id.directors_text)).check(
            matches(
                withText(
                    DirectorsFragment.stringBuilderForDirectors(
                        testMovie.directors!!
                    )
                )
            )
        )
    }

    @Test
    fun testNavToStarActorsFragment_confirmActorsList() {
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<MovieListAdapter.MovieListViewHolder>(
                listItemNo,
                click()
            )
        )
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_star_actors)).perform(closeSoftKeyboard()).perform(click())

//        Verify
        onView(withId(R.id.star_actors_text)).check(
            matches(
                withText(
                    StarActorsFragment.stringBuilderForStarActors(
                        testMovie.star_actors!!
                    )
                )
            )
        )
    }

}