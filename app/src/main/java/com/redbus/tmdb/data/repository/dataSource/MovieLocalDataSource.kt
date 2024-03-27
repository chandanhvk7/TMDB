package com.redbus.tmdb.data.repository.dataSource

import com.redbus.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDB(movieId : Int): Flow<Movie>
}