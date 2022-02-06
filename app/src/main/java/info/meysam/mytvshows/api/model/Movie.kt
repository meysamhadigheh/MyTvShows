package info.meysam.mytvshows.api.model

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import info.meysam.hivaadapter.ItemBinder
import info.meysam.hivaadapter.ItemHolder
import info.meysam.mytvshows.R
import kotlinx.android.synthetic.main.item_movie.view.*


data class Movie(

    val id: Long?=null,
    val adult: Boolean?=null,
    val title: String?=null,
    val original_title: String?=null,
    val overview: String?=null,
    val poster_path: String?=null,
    val backdrop_path: String?=null,
    val vote_average: Float?=null,
    val vote_count: Int?=null,
    val popularity: Float?=null,
    val release_date: String?=null,
    val genre_ids: List<Int>?=null

    ) {

    fun getPosterUrl() = "https://image.tmdb.org/t/p/w500$poster_path"


}

