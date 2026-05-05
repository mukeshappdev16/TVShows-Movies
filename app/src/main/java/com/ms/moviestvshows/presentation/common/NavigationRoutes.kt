package com.ms.moviestvshows.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object Trending

@Serializable
object Movies

@Serializable
object Shows

@Serializable
object Celebrities

@Serializable
object Search

@Serializable
data class MovieDetail(val movieId: Int)

@Serializable
data class TvSeriesDetail(val tvId: Int)

@Serializable
data class CelebrityDetail(val personId: Int)

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector,
)

val topLevelRoutes =
    listOf(
        TopLevelRoute("Trending", Trending, Icons.AutoMirrored.Filled.TrendingUp),
        TopLevelRoute("Movies", Movies, Icons.Default.Movie),
        TopLevelRoute("Shows", Shows, Icons.Default.Tv),
        TopLevelRoute("Celebrities", Celebrities, Icons.Default.Person),
        TopLevelRoute("Search", Search, Icons.Default.Search),
    )
