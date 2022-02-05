package info.meysam.mytvshows.api

class MoviesSearchQueryBuilder : MoviesQueryBuilder() {
    private var page: String? = null
    private var query:String ? =null


    fun setPage(page:Int): MoviesSearchQueryBuilder {
        this.page=page.toString()
        return this
    }
    fun setQuery(query:String): MoviesSearchQueryBuilder {
        this.query=query
        return this
    }

    override fun putQueryParams(queryParams: MutableMap<String, String>) {
        page?.apply { queryParams["page"] = this }
        query?.apply { queryParams["query"] = this }
    }
}
