package info.meysam.mytvshows.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class BelongsToCollection {
    var id: Int? = null
    var name: String? = null
    var poster_path: String? = null
    var backdrop_path: String? = null
}

class BelongsToCollectionTypeConverter {
    @TypeConverter
    fun fromBelongsToCollection(belongsToCollection: BelongsToCollection?): String {
        return Gson().toJson(belongsToCollection)
    }

    @TypeConverter
    fun toBelongsToCollection(belongsToCollection: String?): BelongsToCollection? {
        return Gson().fromJson(belongsToCollection, BelongsToCollection::class.java)
    }
}