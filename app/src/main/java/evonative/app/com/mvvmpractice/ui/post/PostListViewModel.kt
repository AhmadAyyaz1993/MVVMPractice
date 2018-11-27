package evonative.app.com.mvvmpractice.ui.post

import evonative.app.com.mvvmpractice.base.BaseViewModel
import evonative.app.com.mvvmpractice.network.PostApi
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
}