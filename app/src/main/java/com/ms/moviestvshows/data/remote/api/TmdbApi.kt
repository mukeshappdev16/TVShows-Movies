package com.ms.moviestvshows.data.remote.api

import com.ms.moviestvshows.data.remote.dto.MovieListResponseDto
import com.ms.moviestvshows.data.remote.dto.TvSeriesListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): MovieListResponseDto

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): MovieListResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): MovieListResponseDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): MovieListResponseDto

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvSeries(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): TvSeriesListResponseDto

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvSeries(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): TvSeriesListResponseDto

    @GET("tv/popular")
    suspend fun getPopularTvSeries(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): TvSeriesListResponseDto

    @GET("tv/top_rated")
    suspend fun getTopRatedTvSeries(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): TvSeriesListResponseDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}
