package com.vsc.roomdatabase.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vsc.roomdatabase.R
import com.vsc.roomdatabase.repository.MovieRepository
import com.vsc.roomdatabase.view.adapter.MoviesAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentViewMovies() : Fragment() {

    private lateinit var movieRepository: MovieRepository
    private lateinit var recyclerView: RecyclerView
    private var adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_movies, container, false)
        recyclerView = view.findViewById(R.id.movies_recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        movieRepository = MovieRepository.getInstance(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val movies = movieRepository.getAllMovies()
            adapter.setItems(movies)
        }
    }
}