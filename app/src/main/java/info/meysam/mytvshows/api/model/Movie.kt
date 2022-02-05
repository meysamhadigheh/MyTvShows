package info.meysam.mytvshows.api.model


data class Movie(

    val id: Long,
    val adult: Boolean,
    val title: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val vote_average: Float,
    val vote_count: Int,
    val popularity: Float,
    val release_date: String,
    val genre_ids: List<Int>,

    )

