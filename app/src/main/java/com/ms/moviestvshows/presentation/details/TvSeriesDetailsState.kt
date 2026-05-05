package com.ms.moviestvshows.presentation.details

import com.ms.moviestvshows.domain.model.TvSeriesDetails

data class TvSeriesDetailsState(
    val isLoading: Boolean = false,
    val tvSeriesDetails: TvSeriesDetails? = null,
    val error: String? = null,
)
