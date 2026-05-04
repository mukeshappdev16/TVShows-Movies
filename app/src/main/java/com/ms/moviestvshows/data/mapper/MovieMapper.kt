package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.MovieDto
import com.ms.moviestvshows.domain.model.Movie

fun MovieDto.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate ?: "",
    )
