package com.example.movie.di

import com.example.movie.data.repository.IRestApi
import com.example.movie.data.repository.IRoomApi
import com.example.movie.data.repository.Implementations.RepositoryMovie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieModuleBinder {

    @Binds
    @Singleton
    abstract fun bindIRestApi(repositoryMovie: RepositoryMovie): IRestApi

    @Binds
    @Singleton
    abstract fun bindIRoomApi(repositoryMovie: RepositoryMovie): IRoomApi

}