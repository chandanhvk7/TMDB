package com.redbus.tmdb.domain.useCase

data class MovieUseCases(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getMoviesFromDBUseCase: GetMoviesFromDBUseCase,
    val getBookmarkedMovies: GetBookmarkedMovies,
    val addBookmarkedMovie: AddBookmarkedMovie,
    val deleteBookmarkedMovie: DeleteBookmarkedMovie,
    val getOneBookmarkedMovie: GetOneBookmarkedMovie
)