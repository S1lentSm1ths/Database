package com.vsc.roomdatabase.repository

import android.content.Context
import com.vsc.roomdatabase.data.Movie

class MovieRepository private constructor(context: Context) {

    private val movieDao = AppDatabase.getInstance(context).movieDao()

    suspend fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertNewMovie(movie)
    }

    companion object {

        private var instance: MovieRepository? = null
        fun getInstance(context: Context): MovieRepository {
            if (instance == null) {
                instance = MovieRepository(context)
            }
            return instance as MovieRepository
        }
    }
}
