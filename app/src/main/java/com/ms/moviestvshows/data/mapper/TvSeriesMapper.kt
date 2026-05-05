package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.TvSeriesDetailsDto
import com.ms.moviestvshows.data.remote.dto.TvSeriesDto
import com.ms.moviestvshows.domain.model.TvSeries
import com.ms.moviestvshows.domain.model.TvSeriesDetails

fun TvSeriesDto.toDomain(): TvSeries =
    TvSeries(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        firstAirDate = firstAirDate ?: "",
    )

fun TvSeriesDetailsDto.toDomain(): TvSeriesDetails =
    TvSeriesDetails(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        firstAirDate = firstAirDate ?: "",
        genres = genres.map { it.name },
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        status = status,
        tagline = tagline ?: "",
    )
