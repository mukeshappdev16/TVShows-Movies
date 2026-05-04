package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.TrendingItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun multiSearch(query: String): Flow<Result<List<TrendingItem>>>
}
