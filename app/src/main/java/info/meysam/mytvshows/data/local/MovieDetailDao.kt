package info.meysam.mytvshows.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import info.meysam.mytvshows.data.model.MovieDetail

@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM movie_detail_table")
    fun getAll(): List<MovieDetail>

    @Query("SELECT * FROM movie_detail_table WHERE id =:id")
    fun loadMovieDetailById(id: Int): MovieDetail?

    @Insert
    fun insertMovieDetail(vararg movieDetail: MovieDetail)

    @Delete
    fun delete(movieDetail: MovieDetail)
}