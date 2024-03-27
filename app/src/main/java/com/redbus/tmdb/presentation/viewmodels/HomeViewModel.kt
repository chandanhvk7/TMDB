package com.redbus.tmdb.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.domain.useCase.GetPopularMoviesUseCase
import com.redbus.tmdb.domain.useCase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.redbus.tmdb.domain.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
     moviesUseCases: MovieUseCases
    ) : ViewModel() {
    val getAllPopularMovies = moviesUseCases.getPopularMoviesUseCase()
    private val _selectedMovie: MutableStateFlow<Movie?> = MutableStateFlow(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    fun getMovieDetails(movieID: Int) {
        viewModelScope.launch {
            movieUseCases.getMoviesFromDBUseCase.invoke(movieID = movieID).collect {
                _selectedMovie.value = it
            }
        }
    }
}