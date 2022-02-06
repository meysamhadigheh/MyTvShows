package info.meysam.mytvshows.api.model

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import info.meysam.hivaadapter.ItemBinder
import info.meysam.hivaadapter.ItemHolder
import info.meysam.mytvshows.R
import kotlinx.android.synthetic.main.item_movie.view.*


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

    ) {

    fun getPosterUrl() = "https://image.tmdb.org/t/p/w500$poster_path"


}

