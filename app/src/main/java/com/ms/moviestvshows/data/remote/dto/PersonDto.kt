package com.ms.moviestvshows.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val profilePath: String?,
    @SerialName("known_for_department")
    val knownForDepartment: String? = null,
    val popularity: Double? = null,
)

@Serializable
data class PersonListResponseDto(
    val results: List<PersonDto>,
)
