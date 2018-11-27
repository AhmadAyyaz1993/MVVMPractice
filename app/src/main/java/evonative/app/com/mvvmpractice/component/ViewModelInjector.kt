package evonative.app.com.mvvmpractice.component

import dagger.Component
import evonative.app.com.mvvmpractice.module.NetworkModule
import evonative.app.com.mvvmpractice.ui.post.PostListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}