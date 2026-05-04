package com.ms.moviestvshows.presentation.movies

import com.ms.moviestvshows.domain.model.Movie

data class MoviesState(
    val nowPlaying: List<Movie> = emptyList(),
    val popular: List<Movie> = emptyList(),
    val topRated: List<Movie> = emptyList(),
    val upcoming: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
