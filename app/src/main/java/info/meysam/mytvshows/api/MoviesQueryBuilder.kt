package info.meysam.mytvshows.api

import info.meysam.mytvshows.BuildConfig

abstract class MoviesQueryBuilder {

    fun build(): Map<String, String> {
        val queryParams = hashMapOf("api_key" to BuildConfig.API_KEY)
        putQueryParams(queryParams)
        return queryParams
    }

    abstract fun putQueryParams(queryParams: MutableMap<String, String>)

    companion object {

    }
}
