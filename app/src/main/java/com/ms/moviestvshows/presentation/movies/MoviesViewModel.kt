package com.ms.moviestvshows.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.moviestvshows.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
    @Inject
    constructor(
        private val repository: MovieRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(MoviesState())
        val state: StateFlow<MoviesState> = _state.asStateFlow()

        init {
            fetchMovies()
        }

        private fun fetchMovies() {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                repository.getNowPlayingMovies().collectLatest { result ->
                    result.onSuccess { movies ->
                        _state.update { it.copy(nowPlaying = movies, isLoading = false) }
                    }
                    result.onFailure { error ->
                        _state.update { it.copy(error = error.message, isLoading = false) }
                    }
                }
            }
            viewModelScope.launch {
                repository.getPopularMovies().collectLatest { result ->
                    result.onSuccess { movies ->
                        _state.update { it.copy(popular = movies) }
                    }
                }
            }
            viewModelScope.launch {
                repository.getTopRatedMovies().collectLatest { result ->
                    result.onSuccess { movies ->
                        _state.update { it.copy(topRated = movies) }
                    }
                }
            }
            viewModelScope.launch {
                repository.getUpcomingMovies().collectLatest { result ->
                    result.onSuccess { movies ->
                        _state.update { it.copy(upcoming = movies) }
                    }
                }
            }
        }
    }
