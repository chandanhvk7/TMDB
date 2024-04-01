package com.redbus.tmdb.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.redbus.tmdb.BuildConfig
import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.presentation.navigation.Screen




@Composable
fun BookmarkList(
    allMovies:
    State<List<BookmarkedMovie>?>,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    LazyColumn(
        contentPadding = paddingValues
    ) {
        allMovies.value?.let {
            items(it.count(), itemContent = {
                val movie = allMovies.value!![it]
                if (movie != null) {
                    BookmarkListItem(movie = movie, navController = navController)
                }
            })
        }
    }

}

@Composable
fun BookmarkListItem(movie: BookmarkedMovie, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(route = Screen.MovieDetails.passMovieId(movie.movieId.toString())) },
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),

        ) {
        Row(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            movie.posterPath?.let {
                Image(
                    modifier = Modifier
                        .padding(
                            end = 4.dp,
                        )
                        .width(150.dp),
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = BuildConfig.POSTER_URL + movie.posterPath).apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                scale(Scale.FILL)
                            }).build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.height(IntrinsicSize.Max)) {
                movie.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Delete Icon",
                    )
                    movie.rating?.let {
                        Text(text = it, style = MaterialTheme.typography.titleMedium)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}