package com.redbus.tmdb.di

import com.redbus.tmdb.domain.repository.MovieRepository
import com.redbus.tmdb.domain.useCase.GetMoviesFromDBUseCase
import com.redbus.tmdb.domain.useCase.GetPopularMoviesUseCase
import com.redbus.tmdb.domain.useCase.MovieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPopularMoviesUseCase(movieRepository: MovieRepository) = MovieUseCases(
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository = movieRepository),
        getMoviesFromDBUseCase = GetMoviesFromDBUseCase(movieRepository = movieRepository)
    )
}