package com.redbus.tmdb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.redbus.tmdb.presentation.screens.FavoritesScreen

import com.redbus.tmdb.presentation.screens.HomeScreen
import com.redbus.tmdb.presentation.screens.MovieDetailsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.MovieDetails.route,
            arguments = listOf(navArgument("movieId"){
                type= NavType.StringType
            })
        ){
            it.arguments?.getString("movieId")
                ?.let { it1 -> MovieDetailsScreen(navController = navController, movieId = it1) }
        }
        composable(route=Screen.Favorites.route){
            FavoritesScreen(navController = navController)
        }
    }
}