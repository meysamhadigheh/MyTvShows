package info.meysam.mytvshows.api


import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import info.meysam.mytvshows.BuildConfig
import info.meysam.mytvshows.api.model.ErrorResponse
import info.meysam.mytvshows.api.model.GetMoviesResponse
import info.meysam.mytvshows.api.model.MovieDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit


interface MovieService {


    @GET("movie/popular")
    suspend fun getPopularMovies(@QueryMap query: Map<String, String>): NetworkResponse<GetMoviesResponse, ErrorResponse>

    @GET("search/movie")
    suspend fun searchMovies(@QueryMap query: Map<String, String>): NetworkResponse<GetMoviesResponse, ErrorResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int?,
        @QueryMap query: Map<String, String>): NetworkResponse<MovieDetail, ErrorResponse>




    companion object  {

        var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .client(okHttpClient)
                .build()
        }

        val instance: MovieService by lazy { retrofit.create(MovieService::class.java) }
    }
}
