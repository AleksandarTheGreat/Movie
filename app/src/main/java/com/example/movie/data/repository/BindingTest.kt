package com.example.movie.data.repository

import com.example.movie.data.repository.Implementations.RepositoryMovie
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingTest {

    @Binds
    abstract fun bindMovieApi(repositoryMovie: RepositoryMovie): IRestApi

}
