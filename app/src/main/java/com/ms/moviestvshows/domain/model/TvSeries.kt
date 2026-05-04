package com.ms.moviestvshows.domain.model

data class TvSeries(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val firstAirDate: String,
)
