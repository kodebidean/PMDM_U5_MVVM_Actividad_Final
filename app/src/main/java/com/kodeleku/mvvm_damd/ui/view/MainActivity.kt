package com.kodeleku.mvvm_damd.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.firebase.auth.FirebaseAuth
import com.kodeleku.mvvm_damd.R
import com.kodeleku.mvvm_damd.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Redirigir automáticamente a "En Cartelera"
        val intent = Intent(this, MoviesActivity::class.java)
        intent.putExtra("CATEGORY", "now_playing")
        startActivity(intent)
        finish()

        setupToolbar()
        setupDrawer()
        updateHeaderUserInfo()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupDrawer() {
        binding.navigationView.setNavigationItemSelectedListener { item ->
            val category = when (item.itemId) {
                R.id.nav_now_playing -> "now_playing"
                R.id.nav_popular -> "popular"
                R.id.nav_top_rated -> "top_rated"
                R.id.nav_upcoming -> "upcoming"
                else -> null
            }
            category?.let {
                val intent = Intent(this, MoviesActivity::class.java)
                intent.putExtra("CATEGORY", it)
                startActivity(intent)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun updateHeaderUserInfo() {
        val headerView = binding.navigationView.getHeaderView(0)
        val userEmail = headerView.findViewById<TextView>(R.id.tv_user_email)
        userEmail.text = FirebaseAuth.getInstance().currentUser?.email ?: "Usuario desconocido"
    }


    private fun logoutUser() {
        // Aquí llamamos al ViewModel para cerrar sesión
        showToast("Cerrando sesión...")
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
