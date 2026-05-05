package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.CastDto
import com.ms.moviestvshows.data.remote.dto.CrewDto
import com.ms.moviestvshows.domain.model.Cast
import com.ms.moviestvshows.domain.model.Crew

fun CastDto.toDomain(): Cast =
    Cast(
        id = id,
        name = name,
        character = character,
        profilePath = profilePath
    )

fun CrewDto.toDomain(): Crew =
    Crew(
        id = id,
        name = name,
        job = job,
        profilePath = profilePath
    )
