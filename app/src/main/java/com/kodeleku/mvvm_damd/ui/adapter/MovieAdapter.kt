package com.kodeleku.mvvm_damd.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kodeleku.mvvm_damd.R
import com.kodeleku.mvvm_damd.model.FavoriteMovie
import com.kodeleku.mvvm_damd.model.Movie

class MovieAdapter<T>(
    private var movies: MutableList<T>,
    private val onClickListener: (T) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    fun updateData(newMovies: List<T>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize() = movies.size
            override fun getNewListSize() = newMovies.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return when (movies[oldItemPosition]) {
                    is Movie -> (movies[oldItemPosition] as Movie).id == (newMovies[newItemPosition] as Movie).id
                    is FavoriteMovie -> (movies[oldItemPosition] as FavoriteMovie).id == (newMovies[newItemPosition] as FavoriteMovie).id
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return movies[oldItemPosition] == newMovies[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        movies.clear()
        movies.addAll(newMovies)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onClickListener)
    }

    override fun getItemCount() = movies.size
}

