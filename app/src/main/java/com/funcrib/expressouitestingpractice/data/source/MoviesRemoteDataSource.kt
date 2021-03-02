package com.funcrib.expressouitestingpractice.data.source

import com.funcrib.expressouitestingpractice.data.DummyList
import com.funcrib.expressouitestingpractice.data.MovieModel

class MoviesRemoteDataSource : MoviesDataSource {
    private var MOVIES_REMOTE_DATA = LinkedHashMap<Int, MovieModel>(DummyList.movies.size)

    init {
        for (movie in DummyList.movies) {
            addMovie(movie)
        }
    }

    override fun getMovie(movieId: Int): MovieModel? {
        return MOVIES_REMOTE_DATA[movieId]
    }

    override fun getMovies(): List<MovieModel> {
        return ArrayList(MOVIES_REMOTE_DATA.values)
    }

    private fun addMovie(movie: MovieModel) {
        MOVIES_REMOTE_DATA.put(movie.id, movie)
    }
}