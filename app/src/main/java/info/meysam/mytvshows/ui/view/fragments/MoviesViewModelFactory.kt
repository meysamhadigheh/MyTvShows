package info.meysam.mytvshows.ui.view.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MoviesViewModelFactory(

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
