package com.ms.moviestvshows.presentation.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.moviestvshows.domain.repository.TvSeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel
    @Inject
    constructor(
        private val repository: TvSeriesRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(TvSeriesState())
        val state: StateFlow<TvSeriesState> = _state.asStateFlow()

        init {
            fetchTvSeries()
        }

        private fun fetchTvSeries() {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                repository.getAiringTodayTvSeries().collectLatest { result ->
                    result.onSuccess { series ->
                        _state.update { it.copy(airingToday = series, isLoading = false) }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(error = error.message, isLoading = false) }
                    }
                }
            }
            viewModelScope.launch {
                repository.getOnTheAirTvSeries().collectLatest { result ->
                    result.onSuccess { series ->
                        _state.update { it.copy(onTheAir = series) }
                    }
                }
            }
            viewModelScope.launch {
                repository.getPopularTvSeries().collectLatest { result ->
                    result.onSuccess { series ->
                        _state.update { it.copy(popular = series) }
                    }
                }
            }
            viewModelScope.launch {
                repository.getTopRatedTvSeries().collectLatest { result ->
                    result.onSuccess { series ->
                        _state.update { it.copy(topRated = series) }
                    }
                }
            }
        }
    }
