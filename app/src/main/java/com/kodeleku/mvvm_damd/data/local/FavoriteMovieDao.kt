package com.kodeleku.mvvm_damd.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kodeleku.mvvm_damd.model.FavoriteMovie

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(movie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movies")
    suspend fun getAllFavorites(): List<FavoriteMovie>

    @Query("SELECT COUNT(*) FROM favorite_movies WHERE id = :movieId")
    suspend fun isFavorite(movieId: Int): Int

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun removeFromFavorites(movieId: Int)
}
