package com.ms.moviestvshows.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ms.moviestvshows.presentation.celebrites.CelebritiesScreen
import com.ms.moviestvshows.presentation.celebrites.CelebritiesViewModel
import com.ms.moviestvshows.presentation.info.InfoScreen
import com.ms.moviestvshows.presentation.info.InfoViewModel
import com.ms.moviestvshows.presentation.movies.MoviesScreen
import com.ms.moviestvshows.presentation.movies.MoviesViewModel
import com.ms.moviestvshows.presentation.shows.ShowsScreen
import com.ms.moviestvshows.presentation.shows.ShowsViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                topLevelRoutes.forEach { topLevelRoute ->
                    NavigationBarItem(
                        icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
                        label = { Text(topLevelRoute.name) },
                        selected =
                            currentDestination?.hierarchy?.any {
                                it.hasRoute(topLevelRoute.route::class)
                            } == true,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Movies,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable<Movies> {
                val viewModel: MoviesViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                MoviesScreen(state = state)
            }
            composable<Shows> {
                val viewModel: ShowsViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                ShowsScreen(state = state)
            }
            composable<Celebrities> {
                val viewModel: CelebritiesViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                CelebritiesScreen(state = state)
            }
            composable<Info> {
                val viewModel: InfoViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                InfoScreen(state = state)
            }
        }
    }
}
