package info.meysam.mytvshows.repository.impl


import androidx.annotation.WorkerThread
import com.haroldadmin.cnradapter.NetworkResponse
import info.meysam.mytvshows.data.local.MovieDetailDao
import info.meysam.mytvshows.data.remote.MoviesPopularQueryBuilder
import info.meysam.mytvshows.data.remote.MoviesSearchQueryBuilder
import info.meysam.mytvshows.data.remote.MovieService
import info.meysam.mytvshows.data.model.ErrorResponse
import info.meysam.mytvshows.data.model.GetMoviesResponse
import info.meysam.mytvshows.data.model.MovieDetail

class MovieRepository(
    private val movieService: MovieService,
    private val movieDetailDao: MovieDetailDao
) {

    suspend fun getPopularMovies(page: Int?): NetworkResponse<GetMoviesResponse, ErrorResponse> {


        val query = MoviesPopularQueryBuilder()
            .setPage(page ?: 1)
            .build()


        return movieService.getPopularMovies(query)

    }

    suspend fun searchMovies(
        page: Int? = null,
        searchText: String
    ): NetworkResponse<GetMoviesResponse, ErrorResponse> {

        val query = MoviesSearchQueryBuilder()
            .setPage(page ?: 1)
            .setQuery(searchText)
            .build()

        return movieService.searchMovies(query)


    }

    suspend fun getMovieDetail(id: Int): NetworkResponse<MovieDetail, ErrorResponse> {


        val query = MoviesSearchQueryBuilder()
            .build()


        var repo = movieService.getMovieDetail(query = query, id = id)



        //movieDataSource.addMovie(repo.response.body)

        return repo
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movieDetail: MovieDetail) {
        movieDetailDao.insertMovieDetail(movieDetail)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getDetail(id: Int):MovieDetail? {
        return  movieDetailDao.loadMovieDetailById(id)
    }
}



