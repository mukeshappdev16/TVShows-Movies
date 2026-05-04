package com.ms.moviestvshows.domain.model

data class TrendingItem(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val mediaType: String?,
)
