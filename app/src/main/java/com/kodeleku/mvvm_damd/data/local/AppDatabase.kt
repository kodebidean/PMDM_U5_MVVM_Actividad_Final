package com.kodeleku.mvvm_damd.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kodeleku.mvvm_damd.model.FavoriteMovie
import com.kodeleku.mvvm_damd.model.User

@Database(entities = [User::class, FavoriteMovie::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
