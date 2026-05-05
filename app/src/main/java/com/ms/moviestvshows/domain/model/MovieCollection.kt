package com.ms.moviestvshows.domain.model

data class MovieCollection(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val parts: List<Movie>
)
