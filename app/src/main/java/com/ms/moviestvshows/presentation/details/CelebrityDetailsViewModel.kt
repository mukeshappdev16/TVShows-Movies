package com.ms.moviestvshows.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.moviestvshows.domain.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebrityDetailsViewModel @Inject constructor(
    private val repository: PeopleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CelebrityDetailsState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("personId")?.let { personId ->
            getPersonDetails(personId)
        }
    }

    private fun getPersonDetails(personId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getPersonDetails(personId).collect { result ->
                result.onSuccess { details ->
                    _state.update { it.copy(isLoading = false, celebrityDetails = details) }
                }.onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
            }
        }
    }
}
