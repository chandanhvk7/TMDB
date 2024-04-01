package com.redbus.tmdb.domain.useCase

import com.redbus.tmdb.domain.repository.MovieRepository

class DeleteBookmarkedMovie(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId:Int) = movieRepository.deleteBookmarkedMovie(movieId)
}