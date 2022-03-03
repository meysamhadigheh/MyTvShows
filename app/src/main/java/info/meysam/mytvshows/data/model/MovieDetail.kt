package info.meysam.mytvshows.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*


@Entity(tableName = "movie_detail_table")
data class MovieDetail(
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    var belongs_to_collection: BelongsToCollection? = null,
    var budget: Int? = null,
    var genres: List<Genre>? = null,
    var homepage: String? = null,

    @PrimaryKey
    var id: Int? = null,
    var imdb_id: String? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Float? = null,
    var poster_path: String? = null,
    var production_companies: List<ProductionCompany>? = null,
    var production_countries: List<ProductionCountry>? = null,
    var release_date: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var spoken_languages: List<SpokenLanguage>? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var vote_average: Float? = null,
    var vote_count: Int? = null,
) {



    /**
     * change budget format to more readable type that shows k for 1000, ....
     *
     */
    fun getBudget(): String? {

        budget?.let {

            if (it < 1000) return "" + it
            val exp = (Math.log(it.toDouble()) / Math.log(1000.0)).toInt()
            return String.format(
                "%.1f %c",
                it / Math.pow(1000.0, exp.toDouble()),
                "kMGTPE"[exp - 1]
            )

        }


        return budget.toString()

    }


    /**
     * Change date format to show in 3 char month format
     *
     */

    fun getReleaseDate():String{
        val firstDate = release_date
        val englishIsrael = Locale.forLanguageTag("en-US")
        val formatter = SimpleDateFormat("yyyy-MM-dd",englishIsrael)
        val date = formatter.parse(firstDate)
        val desiredFormat = SimpleDateFormat("dd, MMM yyyy",englishIsrael).format(date)
        return desiredFormat
    }
}