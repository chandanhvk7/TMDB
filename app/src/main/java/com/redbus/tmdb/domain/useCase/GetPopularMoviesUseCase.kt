package com.redbus.tmdb.domain.useCase

import com.redbus.tmdb.domain.repository.MovieRepository

class GetPopularMoviesUseCase (private val movieRepository: MovieRepository) {
    suspend operator fun invoke()=movieRepository.getPopularMovies()
}