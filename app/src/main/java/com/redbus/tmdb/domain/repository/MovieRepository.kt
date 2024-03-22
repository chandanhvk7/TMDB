package com.redbus.tmdb.domain.repository

import com.redbus.tmdb.domain.model.MovieList

interface MovieRepository {
    suspend fun getPopularMovies(): Result<MovieList>
}