package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.TvSeriesDto
import com.ms.moviestvshows.domain.model.TvSeries

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
