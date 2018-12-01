package evonative.app.com.mvvmpractice.ui.post

import android.arch.lifecycle.MutableLiveData
import evonative.app.com.mvvmpractice.base.BaseViewModel
import evonative.app.com.mvvmpractice.model.Post

class PostViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }
}