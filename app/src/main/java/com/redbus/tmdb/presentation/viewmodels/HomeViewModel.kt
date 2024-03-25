package com.redbus.tmdb.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.domain.useCase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.redbus.tmdb.domain.util.Result
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _movieState = mutableStateOf<Result<MovieList>>(Result.Loading())
    val movieState: State<Result<MovieList>> = _movieState
    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _movieState.value =  getPopularMoviesUseCase()
        }
    }
}