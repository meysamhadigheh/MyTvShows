package info.meysam.mytvshows.api

class MoviesPopularQueryBuilder : MoviesQueryBuilder() {
    private var page: String? = null


    fun setPage(page:Int): MoviesPopularQueryBuilder {
        this.page=page.toString()
        return this
    }

    override fun putQueryParams(queryParams: MutableMap<String, String>) {
        page?.apply { queryParams["page"] = this }
    }
}
