package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.MovieCollectionDetailsDto
import com.ms.moviestvshows.data.remote.dto.MovieDetailsDto
import com.ms.moviestvshows.data.remote.dto.MovieDto
import com.ms.moviestvshows.domain.model.Movie
import com.ms.moviestvshows.domain.model.MovieCollection
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
        cast = credits?.cast?.map { it.toDomain() } ?: emptyList(),
        crew = credits?.crew?.map { it.toDomain() } ?: emptyList(),
        similar = similar?.results?.map { it.toDomain() } ?: emptyList(),
        collectionId = belongsToCollection?.id,
        trailerKey = videos?.results?.find { it.type == "Trailer" && it.site == "YouTube" }?.key
            ?: videos?.results?.firstOrNull()?.key
    )

fun MovieCollectionDetailsDto.toDomain(): MovieCollection =
    MovieCollection(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        parts = parts.map { it.toDomain() }
    )
