package com.ms.moviestvshows.presentation.shows

import com.ms.moviestvshows.domain.model.TvSeries

data class TvSeriesState(
    val airingToday: List<TvSeries> = emptyList(),
    val onTheAir: List<TvSeries> = emptyList(),
    val popular: List<TvSeries> = emptyList(),
    val topRated: List<TvSeries> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
