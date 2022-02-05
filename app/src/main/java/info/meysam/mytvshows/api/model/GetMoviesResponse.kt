package info.meysam.mytvshows.api.model

data class GetMoviesResponse(

    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int


)