package com.redbus.tmdb.di

import com.redbus.tmdb.data.api.MovieApi
import com.redbus.tmdb.data.repository.dataSource.MovieRemoteDataSource
import com.redbus.tmdb.data.repository.dataSourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideMoviesRemoteDataSource(movieApi: MovieApi) : MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApi)
}