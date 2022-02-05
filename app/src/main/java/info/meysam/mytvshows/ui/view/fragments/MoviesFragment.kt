package info.meysam.mytvshows.ui.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.meysam.mytvshows.R
import info.meysam.mytvshows.api.MoviesService
import info.meysam.mytvshows.repository.impl.MoviesRepository
import info.meysam.mytvshows.ui.adapter.MovieAdapter
import info.meysam.mytvshows.ui.view.activities.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {

    lateinit var viewModel: MoviesViewModel

    private val sharedViewModel: MainActivityViewModel by activityViewModels()

    // this adapter is what we made at hivatec company to help view item in recyclerview
    var moviesAdapter = MovieAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesService = MoviesService.instance

        val moviesRepository = MoviesRepository(moviesService)


        viewModel =
            ViewModelProvider(
                this,
                MoviesViewModelFactory(moviesRepository)
            ).get(MoviesViewModel::class.java)


        initObservers()

        initRecyclerView()

        fetchPopularMovies()


    }

    private fun initRecyclerView() {


        moviesAdapter = MovieAdapter()

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        recycler_movies.adapter = moviesAdapter
        recycler_movies.layoutManager = linearLayoutManager
    }

    private fun fetchPopularMovies() {

        viewModel.getPopularMovies()

    }

    private fun initObservers() {

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->

            movies?.let { moviesAdapter.setMovies(it) }

        })

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressDialog.visibility = View.VISIBLE
            } else {
                progressDialog.visibility = View.GONE
            }
        })


    }

}
