package com.example.movie

import android.app.Application
import android.util.Log
import com.example.movie.data.repository.Implementations.RepositoryMovie
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("Tag", "Application created")
    }

}