package com.redbus.tmdb.di

import com.redbus.tmdb.data.repository.MovieRepositoryImpl
import com.redbus.tmdb.data.repository.dataSource.MovieRemoteDataSource
import com.redbus.tmdb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource)
}