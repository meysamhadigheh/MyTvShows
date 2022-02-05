package info.meysam.mytvshows.ui.view.fragments.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.meysam.mytvshows.repository.impl.MoviesRepository


class MoviesViewModelFactory(

    private val moviesRepository: MoviesRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
