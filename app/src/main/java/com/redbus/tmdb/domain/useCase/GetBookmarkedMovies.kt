package com.redbus.tmdb.domain.useCase

import com.redbus.tmdb.domain.repository.MovieRepository

class GetBookmarkedMovies(private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.getBookmarkedMovies()
}