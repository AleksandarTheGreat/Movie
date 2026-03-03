package com.example.movie.di

import android.content.Context
import com.example.movie.data.api.MovieApi
import com.example.movie.data.room.AppDatabase
import com.example.movie.data.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModuleProvider {

    @Provides
    @Singleton
    fun provideApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<MovieApi>()
    }

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context): MovieDao {
        return AppDatabase.Companion
            .getInstance(context)
            .movieDao()
    }

}