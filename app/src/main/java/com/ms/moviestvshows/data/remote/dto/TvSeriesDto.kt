package com.ms.moviestvshows.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvSeriesDto(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
)

@Serializable
data class TvSeriesListResponseDto(
    val results: List<TvSeriesDto>,
)
