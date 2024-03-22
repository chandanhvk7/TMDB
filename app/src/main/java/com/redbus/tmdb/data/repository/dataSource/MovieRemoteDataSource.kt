package com.redbus.tmdb.data.repository.dataSource

import com.redbus.tmdb.domain.model.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): Response<MovieList>
}