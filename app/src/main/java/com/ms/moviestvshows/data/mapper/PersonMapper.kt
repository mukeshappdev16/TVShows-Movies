package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.PersonDto
import com.ms.moviestvshows.domain.model.Person

fun PersonDto.toDomain(): Person =
    Person(
        id = id,
        name = name,
        profilePath = profilePath,
        knownForDepartment = knownForDepartment ?: "",
        popularity = popularity ?: 0.0,
    )
