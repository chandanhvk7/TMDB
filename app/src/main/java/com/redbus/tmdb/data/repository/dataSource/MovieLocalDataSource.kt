package com.redbus.tmdb.data.repository.dataSource

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.redbus.tmdb.data.db.BookmarksDao
import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDB(movieId : Int): Flow<Movie>
    suspend fun addBookmark(bookmarkedMovie: BookmarkedMovie)

    fun getBookmarkedMovies():Flow<List<BookmarkedMovie>>
    suspend fun deleteOneBookmarked(movieId: Int)

    fun getOneBookmarkedMovie(movieId: Int): BookmarkedMovie?
}
