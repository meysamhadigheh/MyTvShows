package info.meysam.mytvshows.ui.view.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

class MoviesViewModel() : ViewModel() {

    sealed class State {}

    val errorMessage = MutableLiveData<String>()

    var job: Job? = null

    private fun onError(message: String) {
        errorMessage.value = message

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}
