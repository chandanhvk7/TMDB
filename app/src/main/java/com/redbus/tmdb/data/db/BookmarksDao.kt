package com.redbus.tmdb.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redbus.tmdb.domain.model.BookmarkedMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmarkedMovie: BookmarkedMovie)

    @Query("SELECT * FROM bookmarked_movies")
    fun getBookmarkedMovies(): Flow<List<BookmarkedMovie>>
    @Query("DELETE FROM bookmarked_movies WHERE movieId = :movieId")
    suspend fun deleteOneBookmarked(movieId: Int)

    @Query("SELECT * from bookmarked_movies where movieId = :id")
    fun getOneBookmarkedMovie(id: Int):BookmarkedMovie?
}