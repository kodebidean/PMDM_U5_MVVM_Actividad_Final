package com.kodeleku.mvvm_damd.data.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kodeleku.mvvm_damd.R
import com.kodeleku.mvvm_damd.data.local.AppDatabase
import com.kodeleku.mvvm_damd.data.local.FavoriteMovieDao
import com.kodeleku.mvvm_damd.data.local.UserDao
import com.kodeleku.mvvm_damd.data.remote.MoviesApiService
import com.kodeleku.mvvm_damd.data.repository.FavoriteRepository
import com.kodeleku.mvvm_damd.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    // RETROFIT PROVIDES
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApiService(retrofit: Retrofit): MoviesApiService {
        return retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiKey(@ApplicationContext context: Context): String {
        return context.getString(R.string.tmdb_api_key)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: MoviesApiService, apiKey: String): MovieRepository {
        return MovieRepository(apiService, apiKey)
    }

    // FIREBASE PROVIDES
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // ROOM PROVIDES
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration() // Temporalmente, hasta definir migraciones
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    // FAVORITES MOVIES PROVIDES
    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: AppDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteMovieDao: FavoriteMovieDao): FavoriteRepository {
        return FavoriteRepository(favoriteMovieDao)
    }
}
