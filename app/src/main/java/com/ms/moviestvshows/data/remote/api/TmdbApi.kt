package com.ms.moviestvshows.data.remote.api

import com.ms.moviestvshows.data.remote.dto.MovieCollectionDetailsDto
import com.ms.moviestvshows.data.remote.dto.MovieDetailsDto
import com.ms.moviestvshows.data.remote.dto.MovieListResponseDto
import com.ms.moviestvshows.data.remote.dto.PersonDetailsDto
import com.ms.moviestvshows.data.remote.dto.PersonListResponseDto
import com.ms.moviestvshows.data.remote.dto.TrendingResponseDto
import com.ms.moviestvshows.data.remote.dto.TvSeriesDetailsDto
import com.ms.moviestvshows.data.remote.dto.TvSeriesListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "credits,similar",
    ): MovieDetailsDto

    @GET("tv/{tv_id}")
    suspend fun getTvSeriesDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "credits,similar",
    ): TvSeriesDetailsDto

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "combined_credits",
    ): PersonDetailsDto

    @GET("collection/{collection_id}")
    suspend fun getMovieCollection(
        @Path("collection_id") collectionId: Int,
        @Query("api_key") apiKey: String,
    ): MovieCollectionDetailsDto

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

    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): PersonListResponseDto

    @GET("trending/person/{time_window}")
    suspend fun getTrendingPeople(
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): PersonListResponseDto

    @GET("trending/all/{time_window}")
    suspend fun getTrendingAll(
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): TrendingResponseDto

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): MovieListResponseDto

    @GET("trending/tv/{time_window}")
    suspend fun getTrendingTvSeries(
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): TvSeriesListResponseDto

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): TrendingResponseDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}
