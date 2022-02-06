package info.meysam.mytvshows.ui.view.fragments.movies


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.meysam.hivaadapter.HivaRecyclerAdapter
import info.meysam.mytvshows.R
import info.meysam.mytvshows.api.MovieService
import info.meysam.mytvshows.api.model.Movie
import info.meysam.mytvshows.databinding.FragmentMoviesBinding
import info.meysam.mytvshows.repository.impl.MovieRepository
import info.meysam.mytvshows.ui.view.activities.MainActivityViewModel

import info.meysam.mytvshows.utilModule.general.visible
import info.meysam.mytvshows.utilModule.general.gone


class MoviesFragment : Fragment() {

    lateinit var viewModel: MoviesViewModel

    private val sharedViewModel: MainActivityViewModel by activityViewModels()

    var moviesAdapter = HivaRecyclerAdapter()
    lateinit var binding: FragmentMoviesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMoviesBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val moviesService = MovieService.instance

        val moviesRepository = MovieRepository(moviesService)


        viewModel =
            ViewModelProvider(
                this,
                MoviesViewModelFactory(moviesRepository)
            ).get(MoviesViewModel::class.java)


        initObservers()

        initRecyclerView()

        fetchPopularMovies()

        initSearchMoviesListener()


    }


    private fun initObservers() {

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->

            movies?.let {
                moviesAdapter.items=it.toCollection(ArrayList())
                moviesAdapter.notifyDataSetChanged()
                if (it.isEmpty()) binding.emptyLayout.root.visible() else binding.emptyLayout.root.gone()



            }



        })

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })


    }

    private fun initRecyclerView() {


        moviesAdapter = HivaRecyclerAdapter()

        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)

        binding.recyclerMovies.adapter = moviesAdapter
        binding.recyclerMovies.layoutManager = gridLayoutManager

        moviesAdapter.setItemsListener(Movie::class.java, object : Movie.ClickListener {
            override fun itemClicked(movie: Movie) {


                sharedViewModel.setMovieId(movie.id)

                view?.let { Navigation.findNavController(it).navigate(R.id.action_moviesFragment_to_movieDetailFragment) };

            }

        })



    }

    private fun fetchPopularMovies() {

        viewModel.getPopularMovies()

    }

    private fun initSearchMoviesListener() {

        binding.searchEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {

                if (text.isNotEmpty() && text.length >= 3) {

                    searchMovies(text)

                } else {

                    fetchPopularMovies()

                }


            }

        })

    }

    private fun searchMovies(text: CharSequence) {

        val searchText = text.toString()
        viewModel.searchMovies(searchText)

    }

}
