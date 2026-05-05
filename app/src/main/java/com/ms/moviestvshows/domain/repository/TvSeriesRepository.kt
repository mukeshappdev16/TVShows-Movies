package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.TvSeries
import com.ms.moviestvshows.domain.model.TvSeriesDetails
import kotlinx.coroutines.flow.Flow

interface TvSeriesRepository {
    fun getTvSeriesDetails(tvId: Int): Flow<Result<TvSeriesDetails>>

    fun getAiringTodayTvSeries(): Flow<Result<List<TvSeries>>>

    fun getOnTheAirTvSeries(): Flow<Result<List<TvSeries>>>

    fun getPopularTvSeries(): Flow<Result<List<TvSeries>>>

    fun getTopRatedTvSeries(): Flow<Result<List<TvSeries>>>
}
