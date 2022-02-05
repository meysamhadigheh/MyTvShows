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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.meysam.mytvshows.api.MoviesService
import info.meysam.mytvshows.databinding.FragmentMoviesBinding
import info.meysam.mytvshows.repository.impl.MoviesRepository
import info.meysam.mytvshows.ui.adapter.MovieAdapter
import info.meysam.mytvshows.ui.view.activities.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {

    lateinit var viewModel: MoviesViewModel

    private val sharedViewModel: MainActivityViewModel by activityViewModels()

    var moviesAdapter = MovieAdapter()
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

        initSearchMoviesListener()


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
    private fun initRecyclerView() {


        moviesAdapter = MovieAdapter()

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.recyclerMovies.adapter = moviesAdapter
        binding.recyclerMovies.layoutManager = linearLayoutManager
    }
    private fun fetchPopularMovies() {

        viewModel.getPopularMovies()

    }

    private fun initSearchMoviesListener() {

        searchEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {

                if (text.isNotEmpty() && text.length >=3) {

                    searchMovies(text)

                } else {

                    fetchPopularMovies()

                }


            }

        })

    }

    private fun searchMovies(text: CharSequence) {

        val searchText=text.toString()
        viewModel.searchMovies(searchText)

    }

}
