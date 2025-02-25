package com.kodeleku.mvvm_damd.data.remote


import com.google.gson.annotations.SerializedName
import com.kodeleku.mvvm_damd.model.Movie

data class MovieResult(
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int
)
