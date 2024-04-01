package com.redbus.tmdb.data.repository

import androidx.paging.PagingData
import com.redbus.tmdb.BuildConfig
import com.redbus.tmdb.data.repository.dataSource.MovieLocalDataSource
import com.redbus.tmdb.data.repository.dataSource.MovieRemoteDataSource
import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.domain.repository.MovieRepository
import retrofit2.Response
import com.redbus.tmdb.domain.util.Result
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) :
    MovieRepository {
    override fun getPopularMovies() =
        movieRemoteDataSource.getPopularMovies()

    override fun getMoviesFromDB(movieId: Int): Flow<Movie> =
        movieLocalDataSource.getMoviesFromDB(movieId)

    override fun getBookmarkedMovies(): Flow<List<BookmarkedMovie>>  =
        movieLocalDataSource.getBookmarkedMovies()

    override suspend fun deleteBookmarkedMovie(movieId: Int)  =
        movieLocalDataSource.deleteOneBookmarked(movieId)

    override suspend fun addBookmarkMovie(bookmarkedMovie: BookmarkedMovie) =
        movieLocalDataSource.addBookmark(bookmarkedMovie)

    override fun getOneBookmarkedMovie(movieId: Int) =
        movieLocalDataSource.getOneBookmarkedMovie(movieId = movieId)
}