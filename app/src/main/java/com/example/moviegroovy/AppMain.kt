package com.example.moviegroovy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.moviegroovy.ui.components.MenuDrawer
import com.example.moviegroovy.ui.components.TopNavBar
import com.example.moviegroovy.ui.navigation.AppNavGraph
import com.example.moviegroovy.viewModel.DrawerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMain() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerViewModel: DrawerViewModel = hiltViewModel()

    LaunchedEffect(drawerViewModel.isDrawerOpen.value) {
        if (drawerViewModel.isDrawerOpen.value) {
            drawerState.open()
        } else {
            drawerState.close()
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopNavBar(
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { MenuDrawer() },
            modifier = Modifier.padding(innerPadding)
        ) {
            AppNavGraph(navController = navController)
        }
    }

}