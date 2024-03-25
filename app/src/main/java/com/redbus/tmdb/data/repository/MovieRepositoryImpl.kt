package com.redbus.tmdb.data.repository

import com.redbus.tmdb.data.repository.dataSource.MovieRemoteDataSource
import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.domain.repository.MovieRepository
import retrofit2.Response
import com.redbus.tmdb.domain.util.Result

class MovieRepositoryImpl (private val movieRemoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getPopularMovies() = responseToRequest(movieRemoteDataSource.getPopularMovies())

    private fun responseToRequest(response: Response<MovieList>):Result<MovieList>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Result.Success(result)
            }
        }
        return Result.Error(response.message())
    }
}