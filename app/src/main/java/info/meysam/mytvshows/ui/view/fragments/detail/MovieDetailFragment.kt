package info.meysam.mytvshows.ui.view.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import info.meysam.mytvshows.api.MovieService
import info.meysam.mytvshows.databinding.FragmentMovieDetailBinding
import info.meysam.mytvshows.repository.impl.MovieRepository
import info.meysam.mytvshows.ui.view.activities.MainActivityViewModel


class MovieDetailFragment : Fragment() {



    lateinit var viewModel: MovieDetailViewModel

    private val sharedViewModel: MainActivityViewModel by activityViewModels()


    lateinit var binding: FragmentMovieDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val movieService = MovieService.instance

        val movieRepository = MovieRepository(movieService)

        viewModel =
            ViewModelProvider(
                this,
                MovieDetailViewModelFactory(movieRepository)
            ).get(MovieDetailViewModel::class.java)
        initObserver()


        fetchMovieDetail()

    }



    private fun initObserver() {

        viewModel.movie.observe(viewLifecycleOwner){



            it.backdrop_path?.let { backdropPath ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                    .transform(CenterCrop())
                    .into(binding.movieBackdrop)
            }

            it.poster_path?.let { posterPath ->
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w342$posterPath")
                    .transform(CenterCrop())
                    .into(binding.moviePoster)
            }

            binding.movieTitle.text = it.title
            binding.movieRating.rating = (it.vote_average ?:0F )/2
            binding.movieReleaseDate.text = "Release Date : ${it.release_date?.replace("-","/")}"
            binding.movieOverview.text = it.overview
            binding.movieBudget.text="Budget : $${it.getBudget()}"


        }


        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                //binding.detailLoader.visibility = View.VISIBLE
            } else {
                //binding.detailLoader.visibility = View.GONE
            }
        })

    }


    private fun fetchMovieDetail() {

        sharedViewModel.movieId.value?.toInt()?.let { viewModel.loadDetail(it) }


    }


}
