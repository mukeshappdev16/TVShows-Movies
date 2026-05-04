package com.ms.moviestvshows.di

import com.ms.moviestvshows.data.repository.MovieRepositoryImpl
import com.ms.moviestvshows.data.repository.TvSeriesRepositoryImpl
import com.ms.moviestvshows.domain.repository.MovieRepository
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
}
