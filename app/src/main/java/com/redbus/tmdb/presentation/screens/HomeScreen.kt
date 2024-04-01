package com.redbus.tmdb.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.redbus.tmdb.presentation.components.MainAppBar

import com.redbus.tmdb.presentation.components.MovieGridItem
import com.redbus.tmdb.presentation.viewmodels.HomeViewModel
import com.redbus.tmdb.presentation.components.MovieListContent
import com.redbus.tmdb.presentation.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val allMovies = viewModel.getAllPopularMovies.collectAsLazyPagingItems()
    var isListView by rememberSaveable { mutableStateOf(true) }


    Scaffold(
        topBar = {
            MainAppBar("Home",isList = isListView, onLayoutChangeRequested = {isListView=!isListView})
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
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = {
                    navController.navigate(Screen.Home.route)
                }) {
                    Icon(
                        Icons.Filled.Home, contentDescription = "Home")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    navController.navigate(Screen.Favorites.route)
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorites")
                }
            }
        }
    )
}

