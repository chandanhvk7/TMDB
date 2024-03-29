package com.redbus.tmdb.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.redbus.tmdb.R
import com.redbus.tmdb.domain.model.MovieList
import com.redbus.tmdb.presentation.components.MainAppBar

import com.redbus.tmdb.presentation.components.MovieGridItem
import com.redbus.tmdb.presentation.viewmodels.HomeViewModel
import com.redbus.tmdb.presentation.components.MovieListContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val allMovies = viewModel.getAllPopularMovies.collectAsLazyPagingItems()
    var isListView by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            MainAppBar(isList = isListView, onLayoutChangeRequested = {isListView=!isListView})
        },
        content = {padding->

            AnimatedVisibility(visible = !isListView,
                enter = fadeIn(), exit = fadeOut()
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = padding) {
                    items(allMovies.itemCount, itemContent = {
                        val movie=allMovies[it]
                        if(movie!=null){
                            MovieGridItem(movie, navController = navController )
                        }
                    })
                }
            }
            AnimatedVisibility(visible = isListView, enter = fadeIn(), exit = fadeOut()) {
                MovieListContent(allMovies,navController,padding)
            }
        }
    )
}

