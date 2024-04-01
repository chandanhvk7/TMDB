package com.redbus.tmdb.domain.useCase

import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.repository.MovieRepository

class AddBookmarkedMovie(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(bookmarkedMovie: BookmarkedMovie) = movieRepository.addBookmarkMovie(bookmarkedMovie)
}