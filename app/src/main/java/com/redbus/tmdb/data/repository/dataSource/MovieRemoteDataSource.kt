package com.redbus.tmdb.data.repository.dataSource

import androidx.paging.PagingData
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.domain.model.MovieList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRemoteDataSource {
    fun getPopularMovies(): Flow<PagingData<Movie>>
//    suspend fun getMovieDetails(id:Int):Response<MovieList>
}