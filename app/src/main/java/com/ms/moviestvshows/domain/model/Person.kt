package com.ms.moviestvshows.domain.model

data class Person(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val knownForDepartment: String,
    val popularity: Double,
)
