package com.redbus.tmdb.di

import com.redbus.tmdb.domain.repository.MovieRepository
import com.redbus.tmdb.domain.useCase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPopularMoviesUseCase(movieRepository: MovieRepository) =
        GetPopularMoviesUseCase(movieRepository)
}