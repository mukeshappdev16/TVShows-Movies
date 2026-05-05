package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.CombinedCreditDto
import com.ms.moviestvshows.data.remote.dto.PersonDetailsDto
import com.ms.moviestvshows.data.remote.dto.PersonDto
import com.ms.moviestvshows.domain.model.KnownFor
import com.ms.moviestvshows.domain.model.Person
import com.ms.moviestvshows.domain.model.PersonDetails

fun PersonDto.toDomain(): Person =
    Person(
        id = id,
        name = name,
        profilePath = profilePath,
        knownForDepartment = knownForDepartment ?: "",
        popularity = popularity ?: 0.0,
    )

fun PersonDetailsDto.toDomain(): PersonDetails =
    PersonDetails(
        id = id,
        name = name,
        biography = biography,
        birthday = birthday,
        deathday = deathday,
        gender = gender,
        knownForDepartment = knownForDepartment,
        placeOfBirth = placeOfBirth,
        popularity = popularity,
        profilePath = profilePath,
        knownFor = combinedCredits?.cast?.map { it.toDomain() } ?: emptyList()
    )

fun CombinedCreditDto.toDomain(): KnownFor =
    KnownFor(
        id = id,
        title = title ?: name ?: "",
        posterPath = posterPath,
        mediaType = mediaType,
        voteAverage = voteAverage ?: 0.0,
        role = character ?: job
    )
