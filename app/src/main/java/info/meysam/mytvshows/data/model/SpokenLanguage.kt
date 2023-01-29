package info.meysam.mytvshows.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLanguage {
    var english_name: String? = null
    var iso_639_1: String? = null
    var name: String? = null
}

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun fromSpokenLanguageList(spokenLanguages: List<SpokenLanguage>?): String {
        return Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguageList(spokenLanguages: String?): List<SpokenLanguage>? {
        return Gson().fromJson(spokenLanguages, object : TypeToken<List<SpokenLanguage>?>() {}.type)
    }
}