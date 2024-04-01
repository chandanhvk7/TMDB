package com.redbus.tmdb.di

import com.redbus.tmdb.data.db.BookmarksDao
import com.redbus.tmdb.data.db.MovieDao
import com.redbus.tmdb.data.repository.dataSource.MovieLocalDataSource
import com.redbus.tmdb.data.repository.dataSourceImpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(movieDao: MovieDao,bookmarksDao: BookmarksDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao = movieDao, bookmarksDao = bookmarksDao)
}