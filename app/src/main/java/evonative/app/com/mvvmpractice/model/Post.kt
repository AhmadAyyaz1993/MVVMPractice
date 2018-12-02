package evonative.app.com.mvvmpractice.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Post(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)