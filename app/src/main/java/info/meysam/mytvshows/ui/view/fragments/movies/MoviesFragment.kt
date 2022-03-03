package info.meysam.mytvshows.ui.view.fragments.movies


import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import info.meysam.mytvshows.MovieApplication
import info.meysam.mytvshows.R
import info.meysam.mytvshows.data.remote.MovieService
import info.meysam.mytvshows.databinding.FragmentMoviesBinding
import info.meysam.mytvshows.repository.impl.MovieRepository
import info.meysam.mytvshows.ui.adapter.MovieAdapter
import info.meysam.mytvshows.ui.view.activities.MainActivity
import info.meysam.mytvshows.ui.view.activities.MainActivityViewModel

import info.meysam.mytvshows.utilModule.general.visible
import info.meysam.mytvshows.utilModule.general.gone


class MoviesFragment : Fragment() {

    lateinit var viewModel: MoviesViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()

    lateinit var moviesAdapter :MovieAdapter
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


        viewModel =
            ViewModelProvider(
                this,
                MoviesViewModelFactory((requireActivity().application as MovieApplication).repository)
            ).get(MoviesViewModel::class.java)


        initObservers()

        initRecyclerView()

        fetchPopularMovies()

        initSearchMoviesListener()


    }


    private fun initObservers() {

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->

            movies?.let {
                moviesAdapter.setMovies(it)


                /**
                 * Displays a Empty layout when there is not match for user search
                 *
                 */
                if (it.isEmpty()) binding.emptyLayout.root.visible() else binding.emptyLayout.root.gone()

            }

        })

        viewModel.errorMessage.observe(viewLifecycleOwner) {

            displayNetworkError()

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


        moviesAdapter = MovieAdapter(MovieAdapter.OnClickListener{ movie->


                movie.id?.let { sharedViewModel.setMovieId(it) }

                view?.let { Navigation.findNavController(it).navigate(R.id.action_moviesFragment_to_movieDetailFragment) };


        })

        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)

        binding.recyclerMovies.adapter = moviesAdapter
        binding.recyclerMovies.layoutManager = gridLayoutManager


        //region load more
//        binding.recyclerMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                if ((recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition() == moviesAdapter.itemCount/2 - 1 && viewModel.loadMoreStatus) {
//                    //bottom of list!
//                    fetchPopularMovies()
//                }
//
//            }
//
//
//        })

        //endregion


    }


    private fun fetchPopularMovies() {

        viewModel.getPopularMovies(viewModel.pageIndex)

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


    /**
     * Displays a Network error alert dialog to the user
     *
     */
    private fun displayNetworkError() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.oups)
            .setMessage(R.string.search_movies_error)
            .setPositiveButton("retry", DialogInterface.OnClickListener { dialogInterface, i ->
                fetchPopularMovies()
            })
            .setNegativeButton(android.R.string.cancel,DialogInterface.OnClickListener { dialogInterface, i ->
                activity?.onBackPressed()
            })
            .show()
    }

}
