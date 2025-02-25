package com.kodeleku.mvvm_damd.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kodeleku.mvvm_damd.R
import com.kodeleku.mvvm_damd.databinding.ActivityMovieDetailBinding
import com.kodeleku.mvvm_damd.model.Movie
import com.kodeleku.mvvm_damd.ui.viewmodel.FavoriteViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var movie: Movie // ✅ Guardamos la película en una variable global

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener la película desde el intent
        movie = intent.getParcelableExtra<Movie>("MOVIE") ?: return

        // Mostrar la información de la película
        binding.tvMovieTitle.text = movie.title
        binding.tvOverview.text = movie.overview
        Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
            .placeholder(R.drawable.imagen_placeholder)
            .error(R.drawable.noposterp)
            .into(binding.ivMoviePoster)

        // Verificar si la película está en favoritos
        favoriteViewModel.checkIfFavorite(movie.id)
        favoriteViewModel.isFavorite.observe(this) { isFav ->
            binding.btnFavs.setImageResource(
                if (isFav) R.drawable.ic_estrella_llena else R.drawable.ic_estrella_vacia
            )
        }

        // ✅ SOLUCIÓN: Usar la variable `movie`, no `it`
        binding.btnFavs.setOnClickListener {
            favoriteViewModel.toggleFavorite(movie)
        }
    }
}

