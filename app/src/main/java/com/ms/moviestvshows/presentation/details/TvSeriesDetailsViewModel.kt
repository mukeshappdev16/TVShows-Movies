package com.ms.moviestvshows.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.moviestvshows.domain.repository.TvSeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailsViewModel @Inject constructor(
    private val repository: TvSeriesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(TvSeriesDetailsState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("tvId")?.let { tvId ->
            getTvSeriesDetails(tvId)
        }
    }

    private fun getTvSeriesDetails(tvId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getTvSeriesDetails(tvId).collect { result ->
                result.onSuccess { details ->
                    _state.update { it.copy(isLoading = false, tvSeriesDetails = details) }
                }.onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
            }
        }
    }
}
