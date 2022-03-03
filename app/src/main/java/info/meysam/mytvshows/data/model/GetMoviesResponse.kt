package info.meysam.mytvshows.data.model

data class GetMoviesResponse(

    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int


)