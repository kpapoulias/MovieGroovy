package com.example.moviegroovy.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviegroovy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.purple_500),
            titleContentColor = colorResource(R.color.white)
        ),
        title = {
            Text(
                text = "MovieGroovy",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = painterResource(R.drawable.menu_24px),
                    contentDescription = "Menu",
                    tint = colorResource(R.color.white)
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(R.drawable.search_24px),
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
        onMenuClick = {},
        onSearchClick = {}
    )
}
