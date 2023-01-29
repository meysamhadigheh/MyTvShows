package info.meysam.mytvshows.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompany {
    var id: Int? = null
    var logo_path: String? = null
    var name: String? = null
    var origin_country: String? = null
}

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun fromProductionCompanyList(productionCompanies: List<ProductionCompany>?): String {
        return Gson().toJson(productionCompanies)
    }

    @TypeConverter
    fun toProductionCompanyList(productionCompanies: String?): List<ProductionCompany>? {
        return Gson().fromJson(productionCompanies, object : TypeToken<List<ProductionCompany>?>() {}.type)
    }
}