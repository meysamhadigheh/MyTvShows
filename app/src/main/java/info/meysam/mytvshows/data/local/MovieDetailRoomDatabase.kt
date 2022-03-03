package info.meysam.mytvshows.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.meysam.mytvshows.data.model.MovieDetail

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(MovieDetail::class), version = 1, exportSchema = false)
public abstract class MovieDetailRoomDatabase : RoomDatabase() {

   abstract fun movieDetailDao(): MovieDetailDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time. 
        @Volatile
        private var INSTANCE: MovieDetailRoomDatabase? = null

        fun getDatabase(context: Context): MovieDetailRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDetailRoomDatabase::class.java,
                        "movie_detail_database"
                    ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
   }
}
