package com.redbus.tmdb.di

import com.redbus.tmdb.domain.repository.MovieRepository
import com.redbus.tmdb.domain.useCase.AddBookmarkedMovie
import com.redbus.tmdb.domain.useCase.DeleteBookmarkedMovie
import com.redbus.tmdb.domain.useCase.GetBookmarkedMovies
import com.redbus.tmdb.domain.useCase.GetMoviesFromDBUseCase
import com.redbus.tmdb.domain.useCase.GetOneBookmarkedMovie
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
    fun provideMovieUseCases(movieRepository: MovieRepository) = MovieUseCases(
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository = movieRepository),
        getMoviesFromDBUseCase = GetMoviesFromDBUseCase(movieRepository = movieRepository),
        getBookmarkedMovies = GetBookmarkedMovies(movieRepository = movieRepository),
        addBookmarkedMovie = AddBookmarkedMovie(movieRepository = movieRepository),
        deleteBookmarkedMovie = DeleteBookmarkedMovie(movieRepository = movieRepository),
        getOneBookmarkedMovie = GetOneBookmarkedMovie(movieRepository=movieRepository)
    )
}