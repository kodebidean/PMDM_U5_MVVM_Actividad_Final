package com.kodeleku.mvvm_damd.data.repository

import javax.inject.Inject
import com.kodeleku.mvvm_damd.data.remote.MoviesApiService
import com.kodeleku.mvvm_damd.model.Movie

class MovieRepository @Inject constructor(
    private val apiService: MoviesApiService,
    private val apiKey: String
) {
    suspend fun getMovies(category: String, page: Int): List<Movie>? {
        val response = when (category) {
            "now_playing" -> apiService.getNowPlayingMovies(apiKey, page)
            "popular" -> apiService.getPopularMovies(apiKey, page)
            "top_rated" -> apiService.getTopRatedMovies(apiKey, page)
            "upcoming" -> apiService.getUpcomingMovies(apiKey, page)
            else -> null
        }
        return response?.body()?.results
    }
}
