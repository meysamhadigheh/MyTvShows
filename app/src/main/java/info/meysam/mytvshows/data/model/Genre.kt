package info.meysam.mytvshows.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Genre {
    var id: Int? = null
    var name: String? = null
}
class GenreListConverter {
    @TypeConverter
    fun fromString(value: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Genre>): String {
        return Gson().toJson(list)
    }
}