package info.meysam.mytvshows.data.remote

import info.meysam.mytvshows.BuildConfig

abstract class MovieQueryBuilder {

    fun build(): Map<String, String> {
        val queryParams = hashMapOf("api_key" to BuildConfig.API_KEY)
        putQueryParams(queryParams)
        return queryParams
    }

    abstract fun putQueryParams(queryParams: MutableMap<String, String>)

    companion object {

    }
}
