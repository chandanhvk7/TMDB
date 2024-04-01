package com.redbus.tmdb.domain.repository

import androidx.paging.PagingData
import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getMoviesFromDB(movieId: Int): Flow<Movie>
    fun getBookmarkedMovies():Flow<List<BookmarkedMovie>>

    suspend fun deleteBookmarkedMovie(movieId: Int)
    suspend fun addBookmarkMovie(bookmarkedMovie: BookmarkedMovie)

    fun getOneBookmarkedMovie(movieId:Int): BookmarkedMovie?


}