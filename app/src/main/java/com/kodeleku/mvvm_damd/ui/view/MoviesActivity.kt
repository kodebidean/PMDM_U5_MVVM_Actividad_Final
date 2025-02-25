package com.kodeleku.mvvm_damd.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kodeleku.mvvm_damd.R
import com.kodeleku.mvvm_damd.databinding.ActivityMoviesBinding
import com.kodeleku.mvvm_damd.model.Movie
import com.kodeleku.mvvm_damd.ui.adapter.MovieAdapter
import com.kodeleku.mvvm_damd.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var movieAdapter: MovieAdapter<Movie> // ✅ Ahora es global

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar con Drawer
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar navegación del menú lateral
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_now_playing -> navigateToCategory("now_playing")
                R.id.nav_popular -> navigateToCategory("popular")
                R.id.nav_top_rated -> navigateToCategory("top_rated")
                R.id.nav_upcoming -> navigateToCategory("upcoming")
                R.id.nav_favorites -> navigateToFavorites() // ✅ Nueva función para abrir favoritos
                R.id.nav_logout -> logoutUser()
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Configurar RecyclerView UNA SOLA VEZ
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(mutableListOf()) { movie -> onMovieSelected(movie) }
        binding.rvMovies.adapter = movieAdapter

        // ✅ Observar películas solo una vez
        viewModel.movies.observe(this) { movies ->
            movieAdapter.updateData(movies)
        }

        // Cargar la primera lista de películas por defecto o la categoría seleccionada
        val category = intent.getStringExtra("CATEGORY") ?: "now_playing"
        loadMovies(category)

        updateHeaderUserInfo()
    }

    private fun navigateToCategory(category: String) {
        val intent = Intent(this, MoviesActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
        finish()
    }

    private fun navigateToFavorites() {
        val intent = Intent(this, FavoriteActivity::class.java)
        startActivity(intent)
    }

    private fun loadMovies(category: String) {
        supportActionBar?.title = getScreenTitle(category)
        viewModel.loadMovies(category, 1) // ✅ Ahora solo llama al ViewModel, sin recrear `observe()`
    }

    private fun getScreenTitle(category: String): String {
        return when (category) {
            "now_playing" -> "En Cartelera"
            "popular" -> "Populares"
            "top_rated" -> "Mejor Valoradas"
            "upcoming" -> "Próximamente"
            else -> "Películas"
        }
    }

    private fun updateHeaderUserInfo() {
        val headerView = binding.navigationView.getHeaderView(0)
        val userEmail = headerView.findViewById<TextView>(R.id.tv_user_email)
        userEmail.text = FirebaseAuth.getInstance().currentUser?.email ?: "Usuario desconocido"
    }

    private fun logoutUser() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun onMovieSelected(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("MOVIE", movie)
        startActivity(intent)
    }
}
