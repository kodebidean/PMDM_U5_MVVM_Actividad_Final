package com.kodeleku.mvvm_damd.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeleku.mvvm_damd.data.repository.FavoriteRepository
import com.kodeleku.mvvm_damd.model.FavoriteMovie
import com.kodeleku.mvvm_damd.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _favoriteMovies = MutableLiveData<List<FavoriteMovie>>()
    val favoriteMovies: LiveData<List<FavoriteMovie>> = _favoriteMovies

    fun checkIfFavorite(movieId: Int) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(movieId)
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val isFav = repository.isFavorite(movie.id)
            if (isFav) {
                repository.removeFromFavorites(movie.id)
            } else {
                val favoriteMovie = FavoriteMovie(
                    movie.id, movie.title, movie.overview, movie.posterPath, movie.backdropPath
                )
                repository.addToFavorites(favoriteMovie)
            }
            _isFavorite.value = !isFav
        }
    }

    fun getAllFavorites() {
        viewModelScope.launch {
            _favoriteMovies.value = repository.getAllFavorites()
        }
    }
}

