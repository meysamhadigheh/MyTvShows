package info.meysam.mytvshows.api.cache


import info.meysam.mytvshows.api.model.MovieDetail

interface IMovieDataSource {
    /**
     * Search for persisted movie detail which are see before
     *
     * @param movieId
     * @param radius
     * @return object of movie detail
     */
    suspend fun searchMovie( id: Long): MovieDetail?

    /**
     * Persists a given movie dea\tail
     *
     * @param movie
     */
    suspend fun addMovie(movie: MovieDetail)
}
