package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.TvSeries
import kotlinx.coroutines.flow.Flow

interface TvSeriesRepository {
    fun getAiringTodayTvSeries(): Flow<Result<List<TvSeries>>>

    fun getOnTheAirTvSeries(): Flow<Result<List<TvSeries>>>

    fun getPopularTvSeries(): Flow<Result<List<TvSeries>>>

    fun getTopRatedTvSeries(): Flow<Result<List<TvSeries>>>
}
