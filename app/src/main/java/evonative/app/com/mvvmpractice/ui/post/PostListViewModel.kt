package evonative.app.com.mvvmpractice.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import evonative.app.com.mvvmpractice.base.BaseViewModel
import evonative.app.com.mvvmpractice.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject


class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadPosts()
    }

    private fun loadPosts(){
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListError() {

    }

    private fun onRetrievePostListSuccess() {

    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}