package com.ms.moviestvshows.domain.repository

import com.ms.moviestvshows.domain.model.Movie
import com.ms.moviestvshows.domain.model.MovieCollection
import com.ms.moviestvshows.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieDetails(movieId: Int): Flow<Result<MovieDetails>>

    fun getMovieCollection(collectionId: Int): Flow<Result<MovieCollection>>

    fun getNowPlayingMovies(): Flow<Result<List<Movie>>>

    fun getPopularMovies(): Flow<Result<List<Movie>>>

    fun getTopRatedMovies(): Flow<Result<List<Movie>>>

    fun getUpcomingMovies(): Flow<Result<List<Movie>>>
}
