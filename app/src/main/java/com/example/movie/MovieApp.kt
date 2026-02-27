package com.example.movie

import android.app.Application
import android.util.Log
import com.example.movie.data.repository.Implementations.RepositoryMovie

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("Tag", "Application created")
    }

    val repositoryMovie by lazy {
        RepositoryMovie(context = this)
    }
}