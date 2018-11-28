package evonative.app.com.mvvmpractice.network

import evonative.app.com.mvvmpractice.model.Post
import io.reactivex.Observable
import retrofit2.http.GET


interface PostApi {
    /**
     * Get list of posts from API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

}