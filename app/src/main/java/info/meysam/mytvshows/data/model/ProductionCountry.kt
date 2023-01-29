package info.meysam.mytvshows.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountry {
    var iso_3166_1: String? = null
    var name: String? = null
}

class ProductionCountryTypeConverter {
    @TypeConverter
    fun fromProductionCountryList(productionCountries: List<ProductionCountry>?): String {
        return Gson().toJson(productionCountries)
    }

    @TypeConverter
    fun toProductionCountryList(productionCountries: String?): List<ProductionCountry>? {
        return Gson().fromJson(productionCountries, object : TypeToken<List<ProductionCountry>?>() {}.type)
    }
}