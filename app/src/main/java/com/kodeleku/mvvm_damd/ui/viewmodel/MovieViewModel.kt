package com.kodeleku.mvvm_damd.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeleku.mvvm_damd.data.repository.MovieRepository
import com.kodeleku.mvvm_damd.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun loadMovies(category: String, page: Int) {
        viewModelScope.launch {
            val result = repository.getMovies(category, page)
            result?.let { _movies.value = it }
        }
    }
}