package com.example.movie.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.model.Movie
import com.example.movie.data.model.MovieFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieFavorite")
    fun loadAllMovies(): Flow<List<MovieFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieFavorite: MovieFavorite)

    @Delete()
    suspend fun delete(movieFavorite: MovieFavorite)

}