package evonative.app.com.mvvmpractice.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import evonative.app.com.mvvmpractice.model.Post
import evonative.app.com.mvvmpractice.model.dao.PostDao

@Database(entities = arrayOf(Post::class), version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun postDatabase() : PostDao
}