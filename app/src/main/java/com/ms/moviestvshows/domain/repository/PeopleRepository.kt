package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPopularPeople(): Flow<Result<List<Person>>>

    fun getTrendingPeople(timeWindow: String = "week"): Flow<Result<List<Person>>>
}
