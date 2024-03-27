package com.redbus.tmdb.data.api

import retrofit2.http.Query
import com.redbus.tmdb.domain.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Response<MovieList>

//    @GET("movie/{id}")
//    suspend fun getMovieDetails(
//        @Path("id")id:Int,
//        @Query("api_key") apiKey: String
//    ): Response<MovieList>
}