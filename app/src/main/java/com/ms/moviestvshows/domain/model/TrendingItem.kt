package com.ms.moviestvshows.domain.model

data class TrendingItem(
    val id: Int,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val mediaType: String?,
    val voteAverage: Double?,
)
