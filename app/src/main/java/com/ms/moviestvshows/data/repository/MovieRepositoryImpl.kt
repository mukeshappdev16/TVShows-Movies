package com.ms.moviestvshows.data.repository

import com.ms.moviestvshows.BuildConfig
import com.ms.moviestvshows.data.mapper.toDomain
import com.ms.moviestvshows.data.remote.api.TmdbApi
import com.ms.moviestvshows.domain.model.Movie
import com.ms.moviestvshows.domain.model.MovieCollection
import com.ms.moviestvshows.domain.model.MovieDetails
import com.ms.moviestvshows.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl
    @Inject
    constructor(
        private val api: TmdbApi,
    ) : MovieRepository {
        private val apiKey = BuildConfig.TMDB_API_KEY

        override fun getMovieDetails(movieId: Int): Flow<Result<MovieDetails>> =
            flow {
                try {
                    val response = api.getMovieDetails(movieId, apiKey)
                    emit(Result.success(response.toDomain()))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getMovieCollection(collectionId: Int): Flow<Result<MovieCollection>> =
            flow {
                try {
                    val response = api.getMovieCollection(collectionId, apiKey)
                    emit(Result.success(response.toDomain()))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getNowPlayingMovies(): Flow<Result<List<Movie>>> =
            flow {
                try {
                    val response = api.getNowPlayingMovies(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getPopularMovies(): Flow<Result<List<Movie>>> =
            flow {
                try {
                    val response = api.getPopularMovies(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getTopRatedMovies(): Flow<Result<List<Movie>>> =
            flow {
                try {
                    val response = api.getTopRatedMovies(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }

        override fun getUpcomingMovies(): Flow<Result<List<Movie>>> =
            flow {
                try {
                    val response = api.getUpcomingMovies(apiKey)
                    emit(Result.success(response.results.map { it.toDomain() }))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }
    }
