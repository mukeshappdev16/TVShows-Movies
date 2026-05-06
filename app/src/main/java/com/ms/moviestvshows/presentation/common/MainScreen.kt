package com.ms.moviestvshows.presentation.common

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import com.ms.moviestvshows.presentation.details.CelebrityDetailsScreen
import com.ms.moviestvshows.presentation.details.CelebrityDetailsViewModel
import com.ms.moviestvshows.presentation.details.MovieDetailsScreen
import com.ms.moviestvshows.presentation.details.MovieDetailsViewModel
import com.ms.moviestvshows.presentation.details.TvSeriesDetailsScreen
import com.ms.moviestvshows.presentation.details.TvSeriesDetailsViewModel
import com.ms.moviestvshows.presentation.movies.MoviesScreen
import com.ms.moviestvshows.presentation.movies.MoviesViewModel
import com.ms.moviestvshows.presentation.search.SearchScreen
import com.ms.moviestvshows.presentation.search.SearchViewModel
import com.ms.moviestvshows.presentation.shows.ShowsScreen
import com.ms.moviestvshows.presentation.shows.ShowsViewModel
import com.ms.moviestvshows.presentation.trending.TrendingScreen
import com.ms.moviestvshows.presentation.trending.TrendingViewModel

@Composable
fun MainScreen(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val layoutType = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> NavigationSuiteType.NavigationBar
        WindowWidthSizeClass.Medium -> NavigationSuiteType.NavigationRail
        WindowWidthSizeClass.Expanded -> NavigationSuiteType.NavigationRail
        else -> NavigationSuiteType.NavigationBar
    }

    val suiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        )
    )

    NavigationSuiteScaffold(
        layoutType = layoutType,
        containerColor = MaterialTheme.colorScheme.background,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = MaterialTheme.colorScheme.surface,
            navigationRailContainerColor = MaterialTheme.colorScheme.surface
        ),
        navigationSuiteItems = {
            topLevelRoutes.forEach { topLevelRoute ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.hasRoute(topLevelRoute.route::class)
                } == true
                
                item(
                    icon = { 
                        Icon(
                            imageVector = topLevelRoute.icon, 
                            contentDescription = topLevelRoute.name,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                        ) 
                    },
                    label = { 
                        Text(
                            text = topLevelRoute.name, 
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        ) 
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(topLevelRoute.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = suiteItemColors
                )
            }
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = Trending,
        ) {
            composable<Trending> {
                val viewModel: TrendingViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                TrendingScreen(
                    state = state,
                    windowSizeClass = windowSizeClass,
                    onMovieClick = { movieId -> navController.navigate(MovieDetail(movieId)) },
                    onTvSeriesClick = { tvId -> navController.navigate(TvSeriesDetail(tvId)) },
                    onPersonClick = { personId -> navController.navigate(CelebrityDetail(personId)) }
                )
            }
            composable<Movies> {
                val viewModel: MoviesViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                MoviesScreen(
                    state = state,
                    windowSizeClass = windowSizeClass,
                    onMovieClick = { movieId -> navController.navigate(MovieDetail(movieId)) }
                )
            }
            composable<Shows> {
                val viewModel: ShowsViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                ShowsScreen(
                    state = state,
                    windowSizeClass = windowSizeClass,
                    onTvSeriesClick = { tvId -> navController.navigate(TvSeriesDetail(tvId)) }
                )
            }
            composable<Celebrities> {
                val viewModel: CelebritiesViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                CelebritiesScreen(
                    state = state,
                    windowSizeClass = windowSizeClass,
                    onPersonClick = { personId -> navController.navigate(CelebrityDetail(personId)) }
                )
            }
            composable<Search> {
                val viewModel: SearchViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                SearchScreen(
                    state = state,
                    onQueryChange = viewModel::onQueryChange,
                    windowSizeClass = windowSizeClass,
                    onMovieClick = { movieId -> navController.navigate(MovieDetail(movieId)) },
                    onTvSeriesClick = { tvId -> navController.navigate(TvSeriesDetail(tvId)) },
                    onPersonClick = { personId -> navController.navigate(CelebrityDetail(personId)) }
                )
            }
            composable<MovieDetail> {
                val viewModel: MovieDetailsViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                MovieDetailsScreen(
                    state = state,
                    windowSizeClass = windowSizeClass,
                    onBackClick = { navController.popBackStack() },
                    onMovieClick = { movieId -> navController.navigate(MovieDetail(movieId)) },
                    onCastClick = { personId -> navController.navigate(CelebrityDetail(personId)) }
                )
            }
            composable<TvSeriesDetail> {
                val viewModel: TvSeriesDetailsViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                TvSeriesDetailsScreen(
                    state = state,
                    windowSizeClass = windowSizeClass,
                    onBackClick = { navController.popBackStack() },
                    onTvSeriesClick = { tvId -> navController.navigate(TvSeriesDetail(tvId)) },
                    onCastClick = { personId -> navController.navigate(CelebrityDetail(personId)) }
                )
            }
            composable<CelebrityDetail> {
                val viewModel: CelebrityDetailsViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                CelebrityDetailsScreen(
                    state = state,
                    onBackClick = { navController.popBackStack() },
                    onMovieClick = { movieId -> navController.navigate(MovieDetail(movieId)) },
                    onTvSeriesClick = { tvId -> navController.navigate(TvSeriesDetail(tvId)) }
                )
            }
        }
    }
}
