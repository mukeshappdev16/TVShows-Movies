package com.ms.moviestvshows.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String,
    val genres: List<String>,
    val runtime: Int,
    val status: String,
    val tagline: String,
)
