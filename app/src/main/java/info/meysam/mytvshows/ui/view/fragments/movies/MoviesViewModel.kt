package info.meysam.mytvshows.ui.view.fragments.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.meysam.mytvshows.api.model.Movie
import info.meysam.mytvshows.repository.impl.MoviesRepository
import kotlinx.coroutines.*

class MoviesViewModel(

    private val moviesRepository: MoviesRepository
) : ViewModel() {

    sealed class State {}

    private val _movies = MutableLiveData<List<Movie>?>()
    val movies: MutableLiveData<List<Movie>?> = _movies

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    var job: Job? = null


    fun getPopularMovies(page: Int? = 1) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.getPopularMovies(page)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    movies.postValue(response.body()?.results)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }


            }
        }
    }
    fun searchMovies(searchText: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = moviesRepository.searchMovies(searchText =searchText)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    movies.postValue(response.body()?.results)
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
