package com.ms.moviestvshows.presentation.details

import com.ms.moviestvshows.domain.model.MovieCollection
import com.ms.moviestvshows.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetails? = null,
    val movieCollection: MovieCollection? = null,
    val error: String? = null,
)
