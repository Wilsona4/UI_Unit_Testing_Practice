package com.funcrib.expressouitestingpractice.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.data.MovieModel
import com.funcrib.expressouitestingpractice.data.source.MoviesDataSource
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment
constructor(
    val requestOptions: RequestOptions,
    val moviesDataSource: MoviesDataSource
) : Fragment() {

    private lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getInt("movie_id").let { movieId ->
                moviesDataSource.getMovie(movieId)?.let { movieFromRemote ->
                    movie = movieFromRemote
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieDetails()

        movie_directors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_directors", movie?.directors)
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, DirectorsFragment::class.java, bundle)
                addToBackStack("DirectorsFragment")
                commit()
            }
        }

        movie_star_actors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_actors", movie?.star_actors)
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, StarActorsFragment::class.java, bundle)
                addToBackStack("StarActorsFragment")
                commit()
            }
        }
    }

    private fun setMovieDetails() {
        Glide.with(this@MovieDetailFragment)
            .applyDefaultRequestOptions(requestOptions)
            .load(movie.image)
            .into(movie_image)
        movie_title.text = movie.title
        movie_description.text = movie.description
    }
}