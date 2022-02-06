package info.meysam.mytvshows.api.model

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import info.meysam.hivaadapter.ItemBinder
import info.meysam.hivaadapter.ItemHolder
import info.meysam.mytvshows.R
import kotlinx.android.synthetic.main.item_movie.view.*


class Movie(

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

    ):ItemBinder{

    fun getPosterUrl ()= "https://image.tmdb.org/t/p/w500"+poster_path
    override fun getResourceId(): Int {

       return  R.layout.item_movie
    }

    override fun bindToHolder(holder: ItemHolder?, listener: Any?) {
        holder?.itemView?.movie_title?.text = title
        holder?.itemView?.desc?.text = overview
        holder?.itemView?.rate?.text = vote_average.toString()
        Glide.with(holder?.itemView?.context!!).load(getPosterUrl()).into(holder.itemView.image)



        holder.itemView.main_layout.setOnClickListener {

            (listener as ClickListener).itemClicked(this)

        }






        holder.itemView.rateBg.setColorFilter(
            when (vote_average) {

                in 0.toFloat()..4.9.toFloat() -> {

                    ContextCompat.getColor(holder.itemView.context, R.color.rate4)

                }

                in 5.toFloat()..6.9.toFloat() -> {

                    ContextCompat.getColor(holder.itemView.context, R.color.rate5)

                }
                in 7.toFloat()..7.9.toFloat() -> {

                    ContextCompat.getColor(holder.itemView.context, R.color.rate7)

                }

                in 8.toFloat()..10.toFloat() -> {

                    ContextCompat.getColor(holder.itemView.context, R.color.rate8)

                }
                else -> {
                    ContextCompat.getColor(holder.itemView.context, R.color.rate4)
                }



            })

    }

    interface ClickListener {
        fun itemClicked(movie: Movie)
    }
}

