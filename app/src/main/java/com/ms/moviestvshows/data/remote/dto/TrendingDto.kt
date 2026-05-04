package com.ms.moviestvshows.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingItemDto(
    val id: Int,
    val title: String? = null,
    val name: String? = null,
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("profile_path")
    val profilePath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
)

@Serializable
data class TrendingResponseDto(
    val results: List<TrendingItemDto>,
)
