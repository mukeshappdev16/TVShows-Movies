package com.ms.moviestvshows.presentation.info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InfoViewModel
    @Inject
    constructor() : ViewModel() {
        private val _state = MutableStateFlow(InfoState())
        val state: StateFlow<InfoState> = _state.asStateFlow()
    }
