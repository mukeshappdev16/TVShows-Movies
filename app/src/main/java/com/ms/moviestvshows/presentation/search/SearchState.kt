package com.ms.moviestvshows.presentation.search

import com.ms.moviestvshows.domain.model.TrendingItem

data class SearchState(
    val query: String = "",
    val searchResults: List<TrendingItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
