package com.ms.moviestvshows.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.moviestvshows.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject
    constructor(
        private val repository: SearchRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(SearchState())
        val state: StateFlow<SearchState> = _state.asStateFlow()

        fun onQueryChange(newQuery: String) {
            _state.update { it.copy(query = newQuery) }
            if (newQuery.length >= 2) {
                search(newQuery)
            } else {
                _state.update { it.copy(searchResults = emptyList(), error = null) }
            }
        }

        private fun search(query: String) {
            _state.update { it.copy(isLoading = true, error = null) }
            viewModelScope.launch {
                repository.multiSearch(query).collectLatest { result ->
                    result.onSuccess { items ->
                        _state.update { it.copy(searchResults = items, isLoading = false) }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(error = error.message, isLoading = false) }
                    }
                }
            }
        }
    }
