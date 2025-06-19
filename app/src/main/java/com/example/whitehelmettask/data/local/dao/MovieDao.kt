package com.example.whitehelmettask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whitehelmettask.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE `query` = :query ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getMoviesByQuery(query: String, limit: Int, offset: Int): List<MovieEntity>

    @Query("DELETE FROM movies WHERE `query` = :query")
    suspend fun clearMoviesByQuery(query: String)

    @Query("SELECT DISTINCT title FROM movies WHERE title LIKE '%' || :query || '%' LIMIT 10")
    suspend fun getSuggestions(query: String): List<String>
}
