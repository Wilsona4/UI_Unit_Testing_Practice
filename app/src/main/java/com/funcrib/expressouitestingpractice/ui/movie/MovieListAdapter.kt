package com.funcrib.expressouitestingpractice.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.funcrib.expressouitestingpractice.R
import com.funcrib.expressouitestingpractice.data.MovieModel
import com.funcrib.expressouitestingpractice.util.ExpressoIdlingResource
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {

        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    class MovieListViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            movie_title.text = item.title
            Glide.with(itemView)
                .load(item.image)
                .into(movie_image)
            item.star_actors?.let {
                for (index in 0 until it.size) {
                    var appendValue: String = it[index]
                    if (index < (it.size - 1)) {
                        appendValue += ", "
                    }
                    movie_star_actors.append(appendValue)
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: MovieModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieListViewHolder(view, interaction)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<MovieModel>) {
        ExpressoIdlingResource.increment()
        val dataCommitCallback = Runnable {
            ExpressoIdlingResource.decrement()
        }
        differ.submitList(list, dataCommitCallback)
    }
}