package com.ms.moviestvshows.data.repository

import com.ms.moviestvshows.BuildConfig
import com.ms.moviestvshows.data.mapper.toDomain
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.Movie
import com.ms.moviestvshows.domain.model.Person
import com.ms.moviestvshows.domain.model.TrendingItem
import com.ms.moviestvshows.domain.model.TvSeries
import com.ms.moviestvshows.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingRepositoryImpl
    @Inject
    constructor(
        private val api: TmdbApi,
    ) : TrendingRepository {
        private val apiKey = BuildConfig.TMDB_API_KEY

        override fun getTrendingAll(timeWindow: String): Flow<Result<List<TrendingItem>>> =
            flow {
                try {
                    val response = api.getTrendingAll(timeWindow, apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getTrendingMovies(timeWindow: String): Flow<Result<List<Movie>>> =
            flow {
                try {
                    val response = api.getTrendingMovies(timeWindow, apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getTrendingTvSeries(timeWindow: String): Flow<Result<List<TvSeries>>> =
            flow {
                try {
                    val response = api.getTrendingTvSeries(timeWindow, apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getTrendingPeople(timeWindow: String): Flow<Result<List<Person>>> =
            flow {
                try {
                    val response = api.getTrendingPeople(timeWindow, apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }
    }
