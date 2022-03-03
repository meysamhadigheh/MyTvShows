package info.meysam.mytvshows

import android.app.Application
import info.meysam.mytvshows.data.local.MovieDetailRoomDatabase
import info.meysam.mytvshows.data.remote.MovieService
import info.meysam.mytvshows.repository.impl.MovieRepository

class MovieApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MovieDetailRoomDatabase.getDatabase(this) }
    val service by lazy { MovieService.instance }
    val repository by lazy { MovieRepository(service,database.movieDetailDao()) }
}