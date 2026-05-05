package com.ms.moviestvshows.domain.model

data class PersonDetails(
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String?,
    val deathday: String?,
    val gender: Int,
    val knownForDepartment: String,
    val placeOfBirth: String?,
    val popularity: Double,
    val profilePath: String?,
    val knownFor: List<KnownFor>
)

data class KnownFor(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val mediaType: String,
    val voteAverage: Double,
    val role: String? // character or job
)
