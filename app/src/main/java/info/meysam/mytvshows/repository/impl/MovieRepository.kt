package info.meysam.mytvshows.repository.impl


import com.haroldadmin.cnradapter.NetworkResponse
import info.meysam.mytvshows.api.MoviesPopularQueryBuilder
import info.meysam.mytvshows.api.MoviesSearchQueryBuilder
import info.meysam.mytvshows.api.MovieService
import info.meysam.mytvshows.api.model.ErrorResponse
import info.meysam.mytvshows.api.model.GetMoviesResponse
import info.meysam.mytvshows.api.model.MovieDetail

class MovieRepository(private val movieService: MovieService)    {

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

        return movieService.getMovieDetail(query = query, id = id)
    }
}



