package info.meysam.mytvshows.api.model

import com.google.gson.annotations.SerializedName

data class Movie(

     @SerializedName("id") val id: Long,
     @SerializedName("adult") val adult: Boolean,
     @SerializedName("title") val title: String,
     @SerializedName("original_title") val originalTitle: String,
     @SerializedName("overview") val overview: String,
     @SerializedName("poster_path") val posterPath: String,
     @SerializedName("backdrop_path") val backdropPath: String,
     @SerializedName("vote_average") val rating: Float,
     @SerializedName("vote_count") val voteCount: Int,
     @SerializedName("popularity") val popularity: Float,
     @SerializedName("release_date") val releaseDate: String,
     @SerializedName("genre_ids") val genreIds: List<Int>,

 )