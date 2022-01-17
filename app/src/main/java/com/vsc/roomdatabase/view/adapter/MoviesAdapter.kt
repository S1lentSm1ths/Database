package com.vsc.roomdatabase.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vsc.roomdatabase.R
import com.vsc.roomdatabase.data.Movie

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movie = LayoutInflater.from(parent.context).inflate(R.layout.movies_card, parent, false)

        return MovieViewHolder(movie)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieTitle.text = movie.movieName
        holder.movieYear.text = movie.movieYear.toString()
        holder.movieRating.text = movie.movieRating.toString()
    }

    override fun getItemCount() = movieList.size

    fun setItems(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }
}

class MovieViewHolder(movies: View) : RecyclerView.ViewHolder(movies) {
    val movieTitle: TextView = movies.findViewById(R.id.movie_title)
    val movieYear: TextView = movies.findViewById(R.id.movie_year_number)
    val movieRating: TextView = movies.findViewById(R.id.movie_rating_number)
}