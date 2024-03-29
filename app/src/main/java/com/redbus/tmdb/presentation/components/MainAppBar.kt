package com.redbus.tmdb.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.redbus.tmdb.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(isList: Boolean, onLayoutChangeRequested: () -> Unit){
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name))},
        actions = {
            IconButton(onClick = {
                onLayoutChangeRequested()
            }) {
                if (!isList) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = "list view",
                    )
                }
                else {
                    Icon(
                        Icons.Default.GridView,
//                        painter = painterResource(R.drawable.grid_view_icon),
                        contentDescription = "grid view",
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}