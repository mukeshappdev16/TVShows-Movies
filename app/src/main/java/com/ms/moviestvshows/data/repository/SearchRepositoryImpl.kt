package com.ms.moviestvshows.data.repository

import com.ms.moviestvshows.BuildConfig
import com.ms.moviestvshows.data.mapper.toDomain
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.TrendingItem
import com.ms.moviestvshows.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl
    @Inject
    constructor(
        private val api: TmdbApi,
    ) : SearchRepository {
        private val apiKey = BuildConfig.TMDB_API_KEY

        override fun multiSearch(query: String): Flow<Result<List<TrendingItem>>> =
            flow {
                try {
                    val response = api.multiSearch(query, apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }
    }
