package com.example.moviegroovy.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse
}
