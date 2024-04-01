package com.redbus.tmdb.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.redbus.tmdb.BuildConfig
import com.redbus.tmdb.domain.model.BookmarkedMovie
import com.redbus.tmdb.domain.model.Movie
import com.redbus.tmdb.presentation.viewmodels.HomeViewModel

@Composable
fun MovieDetailsContent(
    movie: Movie,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel
) {
//    val movie by viewModel.selectedMovie.collectAsState()
    val isFav by viewModel.isBookmarked.collectAsState()
    movie.let { viewModel.isBookmarked(it.movieId) }
    val scrollState = rememberScrollState()
    Card(modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .fillMaxHeight()){
        Column(
            modifier = Modifier
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = BuildConfig.POSTER_URL + movie.backDrop).apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .height(220.dp),
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    movie.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        if (!isFav) {
                            viewModel.addBookmark(BookmarkedMovie(movie.movieId,movie.overview,movie.posterPath,movie.title,movie.rating,movie.releaseDate,movie.backDrop))
                        }
                        else {
                            viewModel.deleteBookmark(movie.movieId)
                        }                    }) {
                        if (isFav){
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
                        }
                        else{
                            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Not Favorite")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                movie.releaseDate?.let {
                    ReleaseDateComponent(releaseDate = it)
                }
                Spacer(modifier = Modifier.height(8.dp))
                movie.rating?.let {
                    Row{
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(
                                    end = 2.dp,
                                )
                        )
                        Text(text = it)
                    } }
                Spacer(modifier = Modifier.height(16.dp))
                movie.overview?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}