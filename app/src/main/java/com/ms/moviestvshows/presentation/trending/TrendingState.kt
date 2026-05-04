package com.ms.moviestvshows.presentation.trending

import com.ms.moviestvshows.domain.model.Movie
import com.ms.moviestvshows.domain.model.Person
import com.ms.moviestvshows.domain.model.TrendingItem
import com.ms.moviestvshows.domain.model.TvSeries

data class TrendingState(
    val trendingAll: List<TrendingItem> = emptyList(),
    val trendingMovies: List<Movie> = emptyList(),
    val trendingTvSeries: List<TvSeries> = emptyList(),
    val trendingPeople: List<Person> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
