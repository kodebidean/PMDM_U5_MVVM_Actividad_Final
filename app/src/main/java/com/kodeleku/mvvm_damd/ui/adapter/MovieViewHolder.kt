package com.kodeleku.mvvm_damd.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kodeleku.mvvm_damd.R
import com.kodeleku.mvvm_damd.databinding.ItemMovieBinding
import com.kodeleku.mvvm_damd.model.FavoriteMovie
import com.kodeleku.mvvm_damd.model.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovieBinding.bind(view)

    fun <T> bind(movie: T, onClickListener: (T) -> Unit) {
        val title: String
        val posterPath: String

        when (movie) {
            is Movie -> {
                title = movie.title
                posterPath = movie.posterPath
            }
            is FavoriteMovie -> {
                title = movie.title
                posterPath = movie.posterPath
            }
            else -> return
        }

        binding.tvMovieTitle.text = title
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500$posterPath")
            .placeholder(R.drawable.imagen_placeholder)
            .error(R.drawable.noposterp)
            .into(binding.ivMoviePoster)

        binding.root.setOnClickListener {
            onClickListener(movie)
        }
    }
}

