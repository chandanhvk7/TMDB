package com.redbus.tmdb.presentation.screens

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.redbus.tmdb.presentation.components.BookmarkGrid
import com.redbus.tmdb.presentation.components.BookmarkList
import com.redbus.tmdb.presentation.components.MainAppBar
import com.redbus.tmdb.presentation.components.MovieGridItem
import com.redbus.tmdb.presentation.components.MovieListContent
import com.redbus.tmdb.presentation.navigation.Screen
import com.redbus.tmdb.presentation.viewmodels.HomeViewModel

@Composable
fun FavoritesScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val allMovies = viewModel.getBookmarkedMovies.collectAsStateWithLifecycle(null)
    var isListView by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            MainAppBar("Favorites",isList = isListView, onLayoutChangeRequested = {isListView=!isListView})
        },
        content = {padding->

            AnimatedVisibility(visible = !isListView,
                enter = fadeIn(), exit = fadeOut()
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = padding) {
                    allMovies.value?.let {
                        items(it.count(), itemContent = {
                            val movie = allMovies.value!![it]
                            BookmarkGrid(movie, navController = navController)
                        })
                    }
                }
            }
            AnimatedVisibility(visible = isListView, enter = fadeIn(), exit = fadeOut()) {
                BookmarkList(allMovies,navController,padding)
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
                    Icon(
                        Icons.Filled.Favorite, contentDescription = "Favorites")
                }
            }
        }
    )
}