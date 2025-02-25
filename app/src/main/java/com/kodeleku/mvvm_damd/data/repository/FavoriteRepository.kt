package com.kodeleku.mvvm_damd.data.repository

import com.kodeleku.mvvm_damd.data.local.FavoriteMovieDao
import com.kodeleku.mvvm_damd.model.FavoriteMovie
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteMovieDao: FavoriteMovieDao) {
    suspend fun addToFavorites(movie: FavoriteMovie) {
        favoriteMovieDao.addToFavorites(movie)
    }

    suspend fun getAllFavorites(): List<FavoriteMovie> {
        return favoriteMovieDao.getAllFavorites()
    }

    suspend fun isFavorite(movieId: Int): Boolean {
        return favoriteMovieDao.isFavorite(movieId) > 0
    }

    suspend fun removeFromFavorites(movieId: Int) {
        favoriteMovieDao.removeFromFavorites(movieId)
    }
}
