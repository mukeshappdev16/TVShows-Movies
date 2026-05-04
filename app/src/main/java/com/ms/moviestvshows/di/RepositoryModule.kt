package com.ms.moviestvshows.di

import com.ms.moviestvshows.data.repository.MovieRepositoryImpl
import com.ms.moviestvshows.data.repository.PeopleRepositoryImpl
import com.ms.moviestvshows.data.repository.SearchRepositoryImpl
import com.ms.moviestvshows.data.repository.TrendingRepositoryImpl
import com.ms.moviestvshows.data.repository.TvSeriesRepositoryImpl
import com.ms.moviestvshows.domain.repository.MovieRepository
import com.ms.moviestvshows.domain.repository.PeopleRepository
import com.ms.moviestvshows.domain.repository.SearchRepository
import com.ms.moviestvshows.domain.repository.TrendingRepository
import com.ms.moviestvshows.domain.repository.TvSeriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindTvSeriesRepository(tvSeriesRepositoryImpl: TvSeriesRepositoryImpl): TvSeriesRepository

    @Binds
    @Singleton
    abstract fun bindPeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    @Singleton
    abstract fun bindTrendingRepository(trendingRepositoryImpl: TrendingRepositoryImpl): TrendingRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}
