package com.redbus.tmdb.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object MovieDetails:Screen("movie_details_screen/{movieId}"){
        fun passMovieId(movieId:String)="movie_details_screen/$movieId"
    }
    object Favorites:Screen("favorites_screen")
}