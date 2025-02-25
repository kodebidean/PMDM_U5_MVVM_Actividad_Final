package com.kodeleku.mvvm_damd.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodeleku.mvvm_damd.R
import com.kodeleku.mvvm_damd.databinding.ActivityFavoriteBinding
import com.kodeleku.mvvm_damd.databinding.ActivityMoviesBinding
import com.kodeleku.mvvm_damd.model.FavoriteMovie
import com.kodeleku.mvvm_damd.model.Movie
import com.kodeleku.mvvm_damd.ui.adapter.MovieAdapter
import com.kodeleku.mvvm_damd.ui.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding // Ahora usa MoviesBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater) // Usa MoviesBinding
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Películas Favoritas"

        // Configurar RecyclerView con MovieAdapter reutilizado
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val favoriteAdapter = MovieAdapter<FavoriteMovie>(mutableListOf()) { movie ->
            onMovieSelected(movie)
        }
        binding.rvMovies.adapter = favoriteAdapter

        // Cargar favoritos
        favoriteViewModel.getAllFavorites()
        favoriteViewModel.favoriteMovies.observe(this) { movies ->
            favoriteAdapter.updateData(movies)
        }
    }

    private fun onMovieSelected(movie: FavoriteMovie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("MOVIE", Movie(movie.id, movie.title, movie.overview, movie.posterPath, movie.backdropPath))
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
