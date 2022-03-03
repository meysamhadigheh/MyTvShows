package info.meysam.mytvshows.data.local

import androidx.room.*
import info.meysam.mytvshows.data.model.MovieDetail

@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM movie_detail_table")
    suspend fun getAll(): List<MovieDetail>

    @Query("SELECT * FROM movie_detail_table WHERE id =:id")
    suspend fun loadMovieDetailById(id: Int): MovieDetail?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDetail(vararg movieDetail: MovieDetail)

    @Delete
    fun delete(movieDetail: MovieDetail)
}