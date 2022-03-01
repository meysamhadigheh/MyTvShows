package info.meysam.mytvshows.api.remote

class MoviesPopularQueryBuilder : MovieQueryBuilder() {
    private var page: String? = null


    fun setPage(page:Int): MoviesPopularQueryBuilder {
        this.page=page.toString()
        return this
    }

    override fun putQueryParams(queryParams: MutableMap<String, String>) {
        page?.apply { queryParams["page"] = this }
    }
}
