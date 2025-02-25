package com.kodeleku.mvvm_damd.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/now_playing?language=es-ES")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<MovieResult>

    @GET("movie/popular?language=es-ES")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<MovieResult>

    @GET("movie/top_rated?language=es-ES")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<MovieResult>

    @GET("movie/upcoming?language=es-ES")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<MovieResult>
}
