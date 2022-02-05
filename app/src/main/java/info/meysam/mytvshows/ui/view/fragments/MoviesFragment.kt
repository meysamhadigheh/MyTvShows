package info.meysam.mytvshows.ui.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import info.meysam.mytvshows.R
import info.meysam.mytvshows.ui.view.activities.MainActivityViewModel


class MoviesFragment : Fragment() {

    lateinit var viewModel: MoviesViewModel

    private val sharedViewModel: MainActivityViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, MoviesViewModelFactory()).get(MoviesViewModel::class.java)


    }

    private fun initObservers() {


    }

}
