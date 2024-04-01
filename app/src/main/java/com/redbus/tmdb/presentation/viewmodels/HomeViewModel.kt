package com.redbus.tmdb.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.domain.useCase.GetPopularMoviesUseCase
import com.redbus.tmdb.domain.useCase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.redbus.tmdb.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
     moviesUseCases: MovieUseCases
    ) : ViewModel() {
    private val _selectedMovie: MutableStateFlow<Movie?> = MutableStateFlow(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    private val _isBookmarked: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBookmarked:StateFlow<Boolean> = _isBookmarked

    fun getMovieDetails(movieID: Int) {
        viewModelScope.launch {
            movieUseCases.getMoviesFromDBUseCase.invoke(movieID = movieID).collect {
                _selectedMovie.value = it
            }
        }
    }
    fun isBookmarked(movieID:Int){
        val bookmarked = movieUseCases.getOneBookmarkedMovie(movieID)
        _isBookmarked.value = bookmarked!=null
    }
    val getAllPopularMovies = movieUseCases.getPopularMoviesUseCase()
    fun addBookmark(bookmarkedMovie: BookmarkedMovie) = viewModelScope.launch(Dispatchers.IO) {
        _isBookmarked.value = true
        movieUseCases.addBookmarkedMovie(bookmarkedMovie = bookmarkedMovie)
    }

    fun deleteBookmark(movieID: Int) = viewModelScope.launch(Dispatchers.IO) {
        _isBookmarked.value = false
        movieUseCases.deleteBookmarkedMovie(movieId = movieID)

    }

    val getBookmarkedMovies = movieUseCases.getBookmarkedMovies()
}