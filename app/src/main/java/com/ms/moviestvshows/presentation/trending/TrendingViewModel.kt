package com.ms.moviestvshows.presentation.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.moviestvshows.domain.repository.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel
    @Inject
    constructor(
        private val repository: TrendingRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(TrendingState())
        val state: StateFlow<TrendingState> = _state.asStateFlow()

        init {
            fetchTrendingData()
        }

        private fun fetchTrendingData() {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                repository.getTrendingAll().collectLatest { result ->
                    result.onSuccess { items ->
                        _state.update { it.copy(trendingAll = items) }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(error = error.message) }
                    }
                    checkLoadingFinished()
                }
            }
            viewModelScope.launch {
                repository.getTrendingMovies().collectLatest { result ->
                    result.onSuccess { items ->
                        _state.update { it.copy(trendingMovies = items) }
                    }
                    checkLoadingFinished()
                }
            }
            viewModelScope.launch {
                repository.getTrendingTvSeries().collectLatest { result ->
                    result.onSuccess { items ->
                        _state.update { it.copy(trendingTvSeries = items) }
                    }
                    checkLoadingFinished()
                }
            }
            viewModelScope.launch {
                repository.getTrendingPeople().collectLatest { result ->
                    result.onSuccess { items ->
                        _state.update { it.copy(trendingPeople = items) }
                    }
                    checkLoadingFinished()
                }
            }
        }

        private fun checkLoadingFinished() {
            // Simple heuristic: if we have some data or an error, stop loading
            if (_state.value.trendingAll.isNotEmpty() || _state.value.error != null) {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
