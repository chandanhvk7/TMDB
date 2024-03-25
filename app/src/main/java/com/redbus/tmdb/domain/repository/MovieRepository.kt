package com.redbus.tmdb.domain.repository

import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.domain.util.Result

interface MovieRepository {
    suspend fun getPopularMovies(): Result<MovieList>
}