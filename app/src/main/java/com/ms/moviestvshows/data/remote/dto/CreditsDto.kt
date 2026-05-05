package com.ms.moviestvshows.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)

@Serializable
data class CastDto(
    val id: Int,
    val name: String,
    @SerialName("character")
    val character: String,
    @SerialName("profile_path")
    val profilePath: String?
)

@Serializable
data class CrewDto(
    val id: Int,
    val name: String,
    @SerialName("job")
    val job: String,
    @SerialName("profile_path")
    val profilePath: String?
)
