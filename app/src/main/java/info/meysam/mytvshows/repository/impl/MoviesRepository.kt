package info.meysam.mytvshows.repository.impl


import info.meysam.mytvshows.api.MoviesPopularQueryBuilder
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
}



