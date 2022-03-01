package info.meysam.mytvshows.ui.view.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {


    private val _movieId = MutableLiveData<Long>()
    val movieId: LiveData<Long> = _movieId

    fun setMovieId(id: Long) {
        _movieId.value = id
    }



}
