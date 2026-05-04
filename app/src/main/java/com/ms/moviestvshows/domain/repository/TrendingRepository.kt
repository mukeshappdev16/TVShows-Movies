package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.Movie
import com.ms.moviestvshows.domain.model.Person
import com.ms.moviestvshows.domain.model.TrendingItem
import com.ms.moviestvshows.domain.model.TvSeries
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun getTrendingAll(timeWindow: String = "day"): Flow<Result<List<TrendingItem>>>

    fun getTrendingMovies(timeWindow: String = "day"): Flow<Result<List<Movie>>>

    fun getTrendingTvSeries(timeWindow: String = "day"): Flow<Result<List<TvSeries>>>

    fun getTrendingPeople(timeWindow: String = "day"): Flow<Result<List<Person>>>
}
