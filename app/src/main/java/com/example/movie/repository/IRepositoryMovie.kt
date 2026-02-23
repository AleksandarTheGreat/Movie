package com.example.movie.repository

import com.example.movie.model.Movie

interface IRepositoryMovie {
    suspend fun loadAll(): List<Movie>
}