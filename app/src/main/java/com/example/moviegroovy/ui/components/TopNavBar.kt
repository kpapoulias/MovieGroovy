package com.example.moviegroovy.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviegroovy.R
import com.example.moviegroovy.viewModel.DrawerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    scrollBehavior: TopAppBarScrollBehavior?
) {
    val viewModel: DrawerViewModel = hiltViewModel()

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.purple_500),
            scrolledContainerColor = colorResource(R.color.purple_500),
            titleContentColor = colorResource(R.color.white)
        ),
        title = {
            Text(
                text = stringResource(R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                viewModel.toggleDrawer()
            }) {
                Icon(
                    painter = painterResource(R.drawable.menu_24px),
                    contentDescription = "Menu",
                    tint = colorResource(R.color.white)
                )
            }
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = colorResource(R.color.white)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewTopNavBar() {
    TopNavBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    )
}
