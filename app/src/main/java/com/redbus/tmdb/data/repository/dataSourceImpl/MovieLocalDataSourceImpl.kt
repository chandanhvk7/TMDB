package com.redbus.tmdb.data.repository.dataSourceImpl

import com.redbus.tmdb.data.db.MovieDao
import com.redbus.tmdb.data.repository.dataSource.MovieLocalDataSource
import com.redbus.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun getMoviesFromDB(movieId: Int): Flow<Movie> = movieDao.getMovie(movieId)
}