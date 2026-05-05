package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.Person
import com.ms.moviestvshows.domain.model.PersonDetails
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPersonDetails(personId: Int): Flow<Result<PersonDetails>>

    fun getPopularPeople(): Flow<Result<List<Person>>>

    fun getTrendingPeople(timeWindow: String = "week"): Flow<Result<List<Person>>>
}
