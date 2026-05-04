package com.ms.moviestvshows.presentation.info

data class InfoState(
    val info: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)
