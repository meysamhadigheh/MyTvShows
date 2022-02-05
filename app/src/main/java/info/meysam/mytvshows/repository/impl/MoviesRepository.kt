package info.meysam.mytvshows.repository.impl


import info.meysam.mytvshows.api.MoviesPopularQueryBuilder
import info.meysam.mytvshows.api.MoviesSearchQueryBuilder
import info.meysam.mytvshows.api.MoviesService
import info.meysam.mytvshows.api.model.GetMoviesResponse

import retrofit2.Response

class MoviesRepository(private val moviesService: MoviesService)  {

    suspend fun getPopularMovies(page: Int?): Response<GetMoviesResponse> {


        val query = MoviesPopularQueryBuilder()
            .setPage(page ?: 1)
            .build()

        var repo= moviesService.getPopularMovies(query)


        return repo

    }

    suspend fun searchMovies(page: Int? =null,searchText: String): Response<GetMoviesResponse> {

        val query = MoviesSearchQueryBuilder()
            .setPage(page ?: 1)
            .setQuery(searchText)
            .build()

        var repo= moviesService.searchMovies(query)

        return repo


    }
}



