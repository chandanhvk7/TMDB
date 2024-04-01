package com.redbus.tmdb.domain.useCase

import com.redbus.tmdb.domain.repository.MovieRepository

class GetOneBookmarkedMovie(private val movieRepository: MovieRepository) {
    operator fun invoke(movieId:Int) = movieRepository.getOneBookmarkedMovie(movieId = movieId)
}