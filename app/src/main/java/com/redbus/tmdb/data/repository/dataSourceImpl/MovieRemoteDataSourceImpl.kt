package com.redbus.tmdb.data.repository.dataSourceImpl

import com.redbus.tmdb.BuildConfig
import com.redbus.tmdb.data.api.MovieApi
import com.redbus.tmdb.data.repository.dataSource.MovieRemoteDataSource

class MovieRemoteDataSourceImpl (private val movieApi: MovieApi): MovieRemoteDataSource {
    override suspend fun getPopularMovies() = movieApi.getPopularMovies(BuildConfig.API_KEY)
}