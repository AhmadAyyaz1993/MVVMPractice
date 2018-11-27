package evonative.app.com.mvvmpractice.base

import android.arch.lifecycle.ViewModel
import evonative.app.com.mvvmpractice.component.DaggerViewModelInjector
import evonative.app.com.mvvmpractice.component.ViewModelInjector
import evonative.app.com.mvvmpractice.module.NetworkModule
import evonative.app.com.mvvmpractice.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}