package info.meysam.mytvshows.api


import info.meysam.mytvshows.BuildConfig
import info.meysam.mytvshows.api.model.GetMoviesResponse
import info.meysam.mytvshows.utilModule.general.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit


interface MoviesService {


    @GET("movie/popular")
    suspend fun getPopularMovies(@QueryMap query: Map<String, String>): Response<GetMoviesResponse>

    companion object  {

        var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        //    .addInterceptor( NetworkConnectionInterceptor(context))
            .build()
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        val instance: MoviesService by lazy { retrofit.create(MoviesService::class.java) }
    }
}
