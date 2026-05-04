package com.ms.moviestvshows.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object Movies

@Serializable
object Shows

@Serializable
object Celebrities

@Serializable
object Info

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector,
)

val topLevelRoutes =
    listOf(
        TopLevelRoute("Movies", Movies, Icons.Default.Movie),
        TopLevelRoute("Shows", Shows, Icons.Default.Tv),
        TopLevelRoute("Celebrities", Celebrities, Icons.Default.Person),
        TopLevelRoute("Info", Info, Icons.Default.Info),
    )
