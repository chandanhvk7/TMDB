package com.redbus.tmdb.domain.useCase

import com.redbus.tmdb.domain.repository.MovieRepository

class GetMoviesFromDBUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(movieID: Int) = movieRepository.getMoviesFromDB(movieID)
}