package com.ms.moviestvshows.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDetailsDto(
    val id: Int,
    val name: String,
    @SerialName("also_known_as")
    val alsoKnownAs: List<String>? = null,
    val biography: String,
    val birthday: String? = null,
    val deathday: String? = null,
    val gender: Int,
    val homepage: String? = null,
    @SerialName("imdb_id")
    val imdbId: String? = null,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    @SerialName("place_of_birth")
    val placeOfBirth: String? = null,
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String?,
    @SerialName("combined_credits")
    val combinedCredits: CombinedCreditsDto? = null
)

@Serializable
data class CombinedCreditsDto(
    val cast: List<CombinedCreditDto>,
    val crew: List<CombinedCreditDto>
)

@Serializable
data class CombinedCreditDto(
    val id: Int,
    @SerialName("media_type")
    val mediaType: String,
    val title: String? = null,
    val name: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    val character: String? = null,
    val job: String? = null,
)
