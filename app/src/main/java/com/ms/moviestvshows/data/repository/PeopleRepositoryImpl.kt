package com.ms.moviestvshows.data.repository

import com.ms.moviestvshows.BuildConfig
import com.ms.moviestvshows.data.mapper.toDomain
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.Person
import com.ms.moviestvshows.domain.model.PersonDetails
import com.ms.moviestvshows.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl
    @Inject
    constructor(
        private val api: TmdbApi,
    ) : PeopleRepository {
        private val apiKey = BuildConfig.TMDB_API_KEY

        override fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>> =
            flow {
                try {
                    val response = api.getPersonDetails(personId, apiKey)
                    emit(Result.success(response.toDomain()))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getPopularPeople(): Flow<Result<List<Person>>> =
            flow {
                try {
                    val response = api.getPopularPeople(apiKey)
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
