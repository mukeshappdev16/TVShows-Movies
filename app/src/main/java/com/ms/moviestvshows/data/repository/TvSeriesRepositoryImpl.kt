package com.ms.moviestvshows.data.repository

import com.ms.moviestvshows.BuildConfig
import com.ms.moviestvshows.data.mapper.toDomain
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.TvSeries
import com.ms.moviestvshows.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvSeriesRepositoryImpl
    @Inject
    constructor(
        private val api: TmdbApi,
    ) : TvSeriesRepository {
        private val apiKey = BuildConfig.TMDB_API_KEY

        override fun getAiringTodayTvSeries(): Flow<Result<List<TvSeries>>> =
            flow {
                try {
                    val response = api.getAiringTodayTvSeries(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getOnTheAirTvSeries(): Flow<Result<List<TvSeries>>> =
            flow {
                try {
                    val response = api.getOnTheAirTvSeries(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getPopularTvSeries(): Flow<Result<List<TvSeries>>> =
            flow {
                try {
                    val response = api.getPopularTvSeries(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getTopRatedTvSeries(): Flow<Result<List<TvSeries>>> =
            flow {
                try {
                    val response = api.getTopRatedTvSeries(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }
    }
