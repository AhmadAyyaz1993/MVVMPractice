package evonative.app.com.mvvmpractice.network

import android.database.Observable
import evonative.app.com.mvvmpractice.model.Post
import retrofit2.http.GET


interface PostApi {
    /**
     * Get list of posts from API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

}