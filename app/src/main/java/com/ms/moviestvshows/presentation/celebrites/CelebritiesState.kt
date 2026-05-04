package com.ms.moviestvshows.presentation.celebrites

import com.ms.moviestvshows.domain.model.Person

data class CelebritiesState(
    val popularPeople: List<Person> = emptyList(),
    val trendingPeople: List<Person> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
