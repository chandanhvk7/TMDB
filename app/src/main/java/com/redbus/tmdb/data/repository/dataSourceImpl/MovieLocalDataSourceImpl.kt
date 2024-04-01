package com.redbus.tmdb.data.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.redbus.tmdb.data.db.BookmarksDao
import com.redbus.tmdb.data.db.MovieDao
import com.redbus.tmdb.data.paging.MovieRemoteMediator
import com.redbus.tmdb.data.repository.dataSource.MovieLocalDataSource
import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSourceImpl(private val movieDao: MovieDao, private val bookmarksDao: BookmarksDao) : MovieLocalDataSource {
    override fun getMoviesFromDB(movieId: Int): Flow<Movie> = movieDao.getMovie(movieId)
    override suspend fun addBookmark(bookmarkedMovie: BookmarkedMovie) =
        bookmarksDao.addBookmark(bookmarkedMovie)

    override fun getBookmarkedMovies(): Flow<List<BookmarkedMovie>> =
        bookmarksDao.getBookmarkedMovies()

    override suspend fun deleteOneBookmarked(movieId: Int) =
        bookmarksDao.deleteOneBookmarked(movieId)

    override fun getOneBookmarkedMovie(movieId: Int) =
        bookmarksDao.getOneBookmarkedMovie(movieId)

}