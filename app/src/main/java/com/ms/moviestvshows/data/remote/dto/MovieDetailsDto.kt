package com.ms.moviestvshows.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("release_date")
    val releaseDate: String?,
    val genres: List<GenreDto>,
    val runtime: Int?,
    val status: String,
    val tagline: String?,
    @SerialName("belongs_to_collection")
    val belongsToCollection: MovieCollectionDto? = null,
    val credits: CreditsDto? = null,
    val similar: MovieListResponseDto? = null,
    val videos: VideoResponseDto? = null,
)

@Serializable
data class MovieCollectionDto(
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String,
)

@Serializable
data class VideoResponseDto(
    val results: List<VideoDto>
)

@Serializable
data class VideoDto(
    val key: String,
    val site: String,
    val type: String
)
