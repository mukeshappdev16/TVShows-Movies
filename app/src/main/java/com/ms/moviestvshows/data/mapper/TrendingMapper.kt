package com.ms.moviestvshows.data.mapper

import com.ms.moviestvshows.data.remote.dto.TrendingItemDto
import com.ms.moviestvshows.domain.model.TrendingItem

fun TrendingItemDto.toDomain(): TrendingItem =
    TrendingItem(
        id = id,
        title = title ?: name ?: "",
        overview = overview,
        posterPath = posterPath ?: profilePath,
        mediaType = mediaType,
        voteAverage = voteAverage,
    )
