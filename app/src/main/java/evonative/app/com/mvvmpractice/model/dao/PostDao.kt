package evonative.app.com.mvvmpractice.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import evonative.app.com.mvvmpractice.model.Post

@Dao
interface PostDao {

    @get:Query("Select * from post")
    val postsList : List<Post>

    @Insert
    fun insertAll(vararg posts : Post)
}