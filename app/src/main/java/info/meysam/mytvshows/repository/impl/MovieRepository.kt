package info.meysam.mytvshows.repository.impl


import info.meysam.mytvshows.api.MoviesPopularQueryBuilder
import info.meysam.mytvshows.api.MoviesSearchQueryBuilder
import info.meysam.mytvshows.api.MovieService
import info.meysam.mytvshows.api.model.GetMoviesResponse
import info.meysam.mytvshows.api.model.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import retrofit2.Response

class MovieRepository(private val movieService: MovieService)  {

    suspend fun getPopularMovies(page: Int?): Response<GetMoviesResponse> {


        val query = MoviesPopularQueryBuilder()
            .setPage(page ?: 1)
            .build()

        var repo= movieService.getPopularMovies(query)


        return repo

    }

    suspend fun searchMovies(page: Int? =null,searchText: String): Response<GetMoviesResponse> {

        val query = MoviesSearchQueryBuilder()
            .setPage(page ?: 1)
            .setQuery(searchText)
            .build()

        var repo= movieService.searchMovies(query)

        return repo


    }

    suspend fun getMovieDetail(id: Int): Response<MovieDetail> {
        val query = MoviesSearchQueryBuilder()
            .build()

        var repo= movieService.getMovieDetail(query = query, id = id)

        return repo
    }
}



