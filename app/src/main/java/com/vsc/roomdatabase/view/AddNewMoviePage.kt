package com.vsc.roomdatabase.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vsc.roomdatabase.R
import com.vsc.roomdatabase.data.Movie
import com.vsc.roomdatabase.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt

class AddNewMoviePage() : Fragment() {

    private lateinit var movieRepository: MovieRepository

    private lateinit var buttonShowFieldMovie: Button
    private lateinit var buttonViewMovies: Button
    private lateinit var movieNameEditText: EditText
    private lateinit var movieYearEditText: EditText
    private lateinit var movieRatingEditText: EditText
    private lateinit var buttonSave: Button
    private lateinit var movieName: String
    private var movieYear: Int = 0
    private var movieRating: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new_movie_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieRepository = MovieRepository.getInstance(requireContext())
        initializeViews(view)
        showEditViews()
        onButtonShowFieldsClicked()
        onButtonAddClicked()
        onButtonNavigateToSecondScreenClicked(view)
    }

    private fun initializeViews(view: View) {
        buttonShowFieldMovie = view.findViewById(R.id.btnShowEditFields)
        buttonViewMovies = view.findViewById(R.id.button_view_movies)
        buttonSave = view.findViewById(R.id.button_insert_the_movie)
        movieNameEditText = view.findViewById(R.id.movie_name_input)
        movieYearEditText = view.findViewById(R.id.movie_year_input)
        movieRatingEditText = view.findViewById(R.id.movie_rating_input)
    }

    private fun onButtonShowFieldsClicked() {
        buttonShowFieldMovie.setOnClickListener {
            movieNameEditText.visibility = View.VISIBLE
            movieYearEditText.visibility = View.VISIBLE
            movieRatingEditText.visibility = View.VISIBLE
            buttonSave.visibility = View.VISIBLE
        }
    }

    private fun onButtonAddClicked() {
        buttonSave.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                movieName = movieNameEditText.text.toString()
                movieYear = parseInt(movieYearEditText.text.toString())
                movieRating = parseInt(movieRatingEditText.text.toString())
                val movie =
                    Movie(
                        movieName = movieName,
                        movieYear = movieYear,
                        movieRating = movieRating
                    )
                movieRepository.insertMovie(movie)
            }
        }
    }

    private fun onButtonNavigateToSecondScreenClicked(view: View) {
        buttonViewMovies.setOnClickListener {
            findNavController().navigate(R.id.fragmentFirstToFragmentViewMovies)
        }
    }

    private fun showEditViews() {
        movieNameEditText.visibility = View.INVISIBLE
        movieYearEditText.visibility = View.INVISIBLE
        movieRatingEditText.visibility = View.INVISIBLE
        buttonSave.visibility = View.GONE
    }
}
