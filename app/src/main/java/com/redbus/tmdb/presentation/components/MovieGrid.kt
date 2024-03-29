package com.redbus.tmdb.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.redbus.tmdb.BuildConfig
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.presentation.navigation.Screen







@Composable
fun MovieGridItem(movie: Movie, navController: NavHostController) {


        Card(
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .padding(10.dp)
                .clickable { navController.navigate(route = Screen.MovieDetails.passMovieId(movie.movieId.toString())) },
            colors = CardDefaults.cardColors(containerColor = Color.Black)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                movie.posterPath?.let {
                    AsyncImage(
                        model = BuildConfig.POSTER_URL + movie.posterPath,
                        contentDescription = null,
                    )

                }

            }
            Text(
                text = movie.title ?: " ",
                modifier = Modifier.padding(6.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

