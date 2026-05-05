package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.MovieDetailsDto
import com.ms.moviestvshows.data.remote.dto.MovieDto
import com.ms.moviestvshows.domain.model.Movie
import com.ms.moviestvshows.domain.model.MovieDetails

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

fun MovieDetailsDto.toDomain(): MovieDetails =
    MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate ?: "",
        genres = genres.map { it.name },
        runtime = runtime ?: 0,
        status = status,
        tagline = tagline ?: "",
    )
