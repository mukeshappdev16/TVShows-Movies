package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovies(): Flow<Result<List<Movie>>>

    fun getPopularMovies(): Flow<Result<List<Movie>>>

    fun getTopRatedMovies(): Flow<Result<List<Movie>>>

    fun getUpcomingMovies(): Flow<Result<List<Movie>>>
}
