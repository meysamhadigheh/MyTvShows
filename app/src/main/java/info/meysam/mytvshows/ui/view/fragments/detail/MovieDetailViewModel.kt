package info.meysam.mytvshows.ui.view.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.meysam.mytvshows.api.model.MovieDetail
import info.meysam.mytvshows.repository.impl.MovieRepository
import kotlinx.coroutines.*

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    var job: Job? = null


    private val _movie: MutableLiveData<MovieDetail> = MutableLiveData()
    val movie: LiveData<MovieDetail> = _movie

    /**
     * Load the details information about a given movie
     *
     * @param id the movie identifier
     */
    fun loadDetail(id: Int) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = movieRepository.getMovieDetail(id)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    _movie.value= response.body()
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }


            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false


    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}
