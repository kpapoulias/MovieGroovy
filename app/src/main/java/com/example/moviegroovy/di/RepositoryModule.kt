package com.example.moviegroovy.di

import com.example.moviegroovy.data.local.MovieDAO
import com.example.moviegroovy.data.remote.MovieAPI
import com.example.moviegroovy.data.repository.MovieRepository
import com.example.moviegroovy.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI, dao: MovieDAO): MovieRepository {
        return MovieRepositoryImpl(api, dao)
    }
}
