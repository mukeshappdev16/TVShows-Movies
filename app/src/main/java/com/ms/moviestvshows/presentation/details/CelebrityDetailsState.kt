package com.ms.moviestvshows.presentation.details

import com.ms.moviestvshows.domain.model.PersonDetails

data class CelebrityDetailsState(
    val isLoading: Boolean = false,
    val celebrityDetails: PersonDetails? = null,
    val error: String? = null,
)
