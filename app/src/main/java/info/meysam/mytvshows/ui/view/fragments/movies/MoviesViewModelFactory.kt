package info.meysam.mytvshows.ui.view.fragments.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.meysam.mytvshows.repository.impl.MovieRepository


class MoviesViewModelFactory(

    private val movieRepository: MovieRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
