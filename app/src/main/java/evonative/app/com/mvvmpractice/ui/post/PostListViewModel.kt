package evonative.app.com.mvvmpractice.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import evonative.app.com.mvvmpractice.R
import evonative.app.com.mvvmpractice.base.BaseViewModel
import evonative.app.com.mvvmpractice.model.Post
import evonative.app.com.mvvmpractice.model.dao.PostDao
import evonative.app.com.mvvmpractice.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject


class PostListViewModel(private val postDao : PostDao): BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    private lateinit var subscription: Disposable
    val postListAdapter: PostListAdapter = PostListAdapter()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

        val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadPosts()
    }

    private fun loadPosts(){
        subscription = Observable.fromCallable { postDao.postsList }
            .concatMap {
                    dbPostList ->
                if(dbPostList.isEmpty())
                    postApi.getPosts().concatMap {
                            apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result ->  onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrievePostListSuccess(posts : List<Post>) {
        postListAdapter.updatePostList(posts)
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}