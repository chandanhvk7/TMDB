
package com.redbus.tmdb.presentation.ui

//import com.google.accompanist.systemuicontroller.rememberSystemUiController
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.redbus.tmdb.R
import com.redbus.tmdb.domain.util.Result
import com.redbus.tmdb.presentation.viewmodels.HomeViewModel
import com.redbus.tmdb.ui.theme.TMDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(viewModel: HomeViewModel = hiltViewModel()) {
//    val systemUiController = rememberSystemUiController()
//    val systemBarColor = MaterialTheme.colors.statusBarColor
//    val titleColor = MaterialTheme.colors.titleColor
//    val topAppbarBackgroundColor = MaterialTheme.colors.topAppbarBackgroundColor

//    SideEffect {
//        systemUiController.setStatusBarColor(
//            color = systemBarColor
//        )
//    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_name))
            },
        )
    }) {
        when (val movieResponse = viewModel.movieState.value) {
            is Result.Loading -> {}//ProgressBar()
            is Result.Success -> {
                Log.d("Got Movies", movieResponse.data.toString())
//                LazyColumn(
//                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
//                ) {
//                    movieResponse.data?.let { movieList ->
////                        items(
////                        items = movieList.movies,
////                        itemContent = {
////                            MovieListItem(movie = it)
////                        }
////                        )
//                    }
//                }
            }

            is Result.Error -> Toast.makeText(
                LocalContext.current,
                stringResource(R.string.toast_error),
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}

