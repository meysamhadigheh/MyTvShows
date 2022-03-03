package info.meysam.mytvshows.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import info.meysam.mytvshows.R
import info.meysam.mytvshows.data.model.Movie
import info.meysam.mytvshows.databinding.ItemMovieBinding

class MovieAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]

        holder.binding.movieTitle.text = movie.title
        holder.binding.desc.text = movie.overview
        holder.binding.rate.text = movie.vote_average.toString()
        Glide.with(holder.itemView.context).load(movie.getPosterUrl()).into(holder.binding.image)


        /**
         * change badge color based on the rating
         *
         */
        holder.binding.rateBg.setColorFilter(
            when (movie.vote_average?:0F) {

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

        holder.binding.mainLayout.setOnClickListener {

            onClickListener.onClick(movie)

        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class OnClickListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }
}

class MainViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}