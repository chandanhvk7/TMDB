package com.redbus.tmdb.data.api

import retrofit2.http.Query
import com.redbus.tmdb.domain.model.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieList>
}