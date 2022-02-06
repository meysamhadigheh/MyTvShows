package info.meysam.mytvshows.api.cache.impl

import androidx.annotation.VisibleForTesting

import info.meysam.mytvshows.api.cache.IMovieDataSource
import info.meysam.mytvshows.api.model.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource(private val maxCacheSize: Int = DEFAULT_MAX_CACHE_SIZE) : IMovieDataSource {

    companion object {
        const val DEFAULT_MAX_CACHE_SIZE = 500
    }

    //We use a Set in order to avoid duplicated elements
    //We use a LinkedHashSet in order to keep the elements ordered following the insertion order
    // because at some point we will remove the oldest ones.
    @VisibleForTesting
    val cache = LinkedHashSet<MovieDetail>()


    override suspend fun searchMovie(id: Long): MovieDetail? {
        return withContext(Dispatchers.Default) {




            (cache.filter { it.id!! <= id})[0]
        }
    }

    override suspend fun addMovie(movie: MovieDetail) {

        withContext(Dispatchers.Default) {
            //If we are going to exceed the cache size by inserting new elements,
            // then we need to remove the oldest element in the cache which is the ones on the first positions
            if (cache.size > maxCacheSize) {
                var elementToRemoveCount = (cache.size) - maxCacheSize
                cache.removeAll {
                    elementToRemoveCount-- > 0
                }
            }
            cache.add(movie)
        }
    }
}

