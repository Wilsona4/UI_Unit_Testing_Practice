package com.funcrib.expressouitestingpractice.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bumptech.glide.request.RequestOptions
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.data.MovieModel
import com.funcrib.expressouitestingpractice.data.source.MoviesRemoteDataSource
import com.funcrib.expressouitestingpractice.factory.MovieFragmentFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    @Test
    fun test_isMovieDataVisible() {
//        Setup
        val movieId = 1
        val title = "The Rundown"
        val description =
            "A tough aspiring chef is hired to bring home a mobster's son from the Amazon but " +
                    "becomes involved in the fight against an oppressive town operator and the search " +
                    "for a legendary treasure."

        val movie = MovieModel(
            movieId,
            title,
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Rundown-the_rundown.png",
            description,
            arrayListOf("R.J. Stewart", "James Vanderbilt"),
            arrayListOf(
                "Dwayne Johnson",
                "Seann William Scott",
                "Rosario Dawson",
                "Christopher Walken"
            )
        )

        val moviesDataSource = mockk<MoviesRemoteDataSource>()
        every {
            moviesDataSource.getMovie(movieId)
        } returns movie

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)

        val fragmentFactory = MovieFragmentFactory(requestOptions, moviesDataSource)
        val bundle = Bundle()
        bundle.putInt("movie_id", movie.id)

        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

//        Verify
        onView(withId(R.id.movie_title)).check(matches(withText(movie.title)))

        onView(withId(R.id.movie_description)).check(matches(withText(movie.description)))
    }
}