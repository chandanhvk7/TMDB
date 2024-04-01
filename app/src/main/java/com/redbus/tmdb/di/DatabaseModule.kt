package com.redbus.tmdb.di

import android.app.Application
import androidx.room.Room
import com.redbus.tmdb.data.db.BookmarksDao
import com.redbus.tmdb.data.db.MovieDB
import com.redbus.tmdb.data.db.MovieDao
import com.redbus.tmdb.data.db.MovieRemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): MovieDB =
        Room.databaseBuilder(app, MovieDB::class.java, "movie_db").fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()

    @Provides
    fun provideMovieDao(movieDB: MovieDB) : MovieDao = movieDB.movieDao()

    @Provides
    fun provideMovieRemoteKeysDao(movieDB: MovieDB) : MovieRemoteKeysDao = movieDB.movieRemoteKeysDao()

    @Provides
    fun provideBookmarksDao(movieDB: MovieDB):BookmarksDao=movieDB.bookmarksDao()
}