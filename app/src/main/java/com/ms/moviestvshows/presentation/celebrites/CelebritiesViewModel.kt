package com.ms.moviestvshows.presentation.celebrites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.moviestvshows.domain.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebritiesViewModel
    @Inject
    constructor(
        private val repository: PeopleRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CelebritiesState())
        val state: StateFlow<CelebritiesState> = _state.asStateFlow()

        init {
            fetchPeople()
        }

        private fun fetchPeople() {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                repository.getPopularPeople().collectLatest { result ->
                    result.onSuccess { people ->
                        _state.update { it.copy(popularPeople = people) }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(error = error.message) }
                    }
                    checkLoadingFinished()
                }
            }
            viewModelScope.launch {
                repository.getTrendingPeople().collectLatest { result ->
                    result.onSuccess { people ->
                        _state.update { it.copy(trendingPeople = people) }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(error = error.message) }
                    }
                    checkLoadingFinished()
                }
            }
        }

        private fun checkLoadingFinished() {
            if (_state.value.popularPeople.isNotEmpty() ||
                _state.value.trendingPeople.isNotEmpty() ||
                _state.value.error != null
            ) {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
