package info.meysam.mytvshows.api.model

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(

    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("total_results") val totalResults: Int


 )