package com.ms.moviestvshows.domain.model

data class TvSeriesDetails(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val firstAirDate: String,
    val genres: List<String>,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val status: String,
    val tagline: String,
    val seasons: List<Season>,
    val cast: List<Cast>,
    val crew: List<Crew>,
    val similar: List<TvSeries>,
    val trailerKey: String? = null,
)
