package info.meysam.mytvshows.ui.view.fragments.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import info.meysam.mytvshows.api.model.LoadMoreItem
import info.meysam.mytvshows.api.model.Movie
import info.meysam.mytvshows.repository.impl.MovieRepository
import kotlinx.coroutines.*

class MoviesViewModel(

    private val movieRepository: MovieRepository
) : ViewModel() {

    sealed class State {}

    private val _movies = MutableLiveData<List<Movie>?>()
    val movies: MutableLiveData<List<Movie>?> = _movies

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    var pageIndex: Int = 1

    var loadMoreStatus = false
    var loadMoreItem: LoadMoreItem? = null


    var job: Job? = null


    fun getPopularMovies(page: Int? = 1) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = movieRepository.getPopularMovies(page)
            withContext(Dispatchers.Main) {

                when (response) {
                    is NetworkResponse.Success -> {

                        movies.postValue(response.body.results)
                        loading.value = false
                    }
                    is NetworkResponse.Error -> {

                        onError("Error : ${response.error?.message} ")
                        loading.value = false
                    }

                }

            }
        }
    }
    fun searchMovies(searchText: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = movieRepository.searchMovies(searchText =searchText)
            withContext(Dispatchers.Main) {

                when (response) {
                    is NetworkResponse.Success -> {

                        movies.postValue(response.body.results)
                        loading.value = false
                    }
                    is NetworkResponse.Error -> {

                        onError("Error : ${response.error?.message} ")
                        loading.value = false
                    }

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
