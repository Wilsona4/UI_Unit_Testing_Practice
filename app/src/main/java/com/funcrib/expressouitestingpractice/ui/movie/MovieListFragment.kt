package com.funcrib.expressouitestingpractice.ui.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.data.DummyList.NETWORK_DELAY
import com.funcrib.expressouitestingpractice.data.MovieModel
import com.funcrib.expressouitestingpractice.data.source.MoviesDataSource
import com.funcrib.expressouitestingpractice.util.UICommunicationListener
import com.funcrib.expressouitestingpractice.util.ExpressoIdlingResource
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MovieListFragment(
    val moviesDataSource: MoviesDataSource
) : Fragment(), MovieListAdapter.Interaction {

    private val TAG = "App Debug"
    lateinit var listAdapter: MovieListAdapter
    private var uiCommunicationListener: UICommunicationListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        getData()
    }

    private fun getData() {
        ExpressoIdlingResource.increment()
        uiCommunicationListener?.loading(true)

        val job = GlobalScope.launch(IO) {
            delay(NETWORK_DELAY)
        }
        job.invokeOnCompletion {
            GlobalScope.launch(Main){
                uiCommunicationListener?.loading(false)
                listAdapter.submitList(moviesDataSource.getMovies())
                ExpressoIdlingResource.decrement()
            }
        }

    }

    private fun initializeRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
//            You can add item decorations
            listAdapter = MovieListAdapter(this@MovieListFragment)
            adapter = listAdapter
        }
    }

    override fun onItemSelected(position: Int, item: MovieModel) {
        activity?.run {
            val bundle = Bundle()
            bundle.putInt("movie_id", item.id)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .addToBackStack("MovieDetailFragment")
                .commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            uiCommunicationListener = context as UICommunicationListener?
        }catch (e: ClassCastException){
            Log.e(TAG, "Must implement interface in $activity: ${e.message}")
        }
    }
}