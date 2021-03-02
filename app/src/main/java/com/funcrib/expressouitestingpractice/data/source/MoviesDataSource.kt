package com.funcrib.expressouitestingpractice.data.source

import com.funcrib.expressouitestingpractice.data.MovieModel

interface MoviesDataSource {
    fun getMovie(movieId: Int): MovieModel?

    fun getMovies(): List<MovieModel>
}