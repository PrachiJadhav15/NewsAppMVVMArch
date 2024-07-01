package com.prachi.newsappmvvmarch.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.prachi.newsappmvvmarch.data.repository.NewsListRepository
import com.prachi.newsappmvvmarch.data.repository.NewsSourceRepository
import com.prachi.newsappmvvmarch.data.repository.TopHeadlineRepository
import com.prachi.newsappmvvmarch.di.ActivityContext
import com.prachi.newsappmvvmarch.ui.base.ViewModelProviderFactory
import com.prachi.newsappmvvmarch.ui.country.CountryListAdapter
import com.prachi.newsappmvvmarch.ui.country.CountryListViewModel
import com.prachi.newsappmvvmarch.ui.language.LanguageSelectionAdapter
import com.prachi.newsappmvvmarch.ui.language.LanguageViewModel
import com.prachi.newsappmvvmarch.ui.newslist.NewsListAdapter
import com.prachi.newsappmvvmarch.ui.newslist.NewsListViewModel
import com.prachi.newsappmvvmarch.ui.newssource.NewsSourcesAdapter
import com.prachi.newsappmvvmarch.ui.newssource.NewsSourcesViewModel
import com.prachi.newsappmvvmarch.ui.topheadline.TopHeadlineAdapter
import com.prachi.newsappmvvmarch.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsSourcesViewModel(newsSourceRepository: NewsSourceRepository): NewsSourcesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourcesViewModel::class) {
                NewsSourcesViewModel(newsSourceRepository)
            })[NewsSourcesViewModel::class.java]
    }

    @Provides
    fun provideNewsSourcesAdapter() = NewsSourcesAdapter(ArrayList())

    @Provides
    fun provideNewsListBySourceViewModel(newsListRepository: NewsListRepository): NewsListViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsListViewModel::class) {
                NewsListViewModel(newsListRepository)
            })[NewsListViewModel::class.java]
    }

    @Provides
    fun provideNewsListAdapter() = NewsListAdapter(ArrayList())

    @Provides
    fun provideCountryListViewModel(): CountryListViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(CountryListViewModel::class) {
                CountryListViewModel()
            })[CountryListViewModel::class.java]
    }

    @Provides
    fun provideCountryListAdapter() = CountryListAdapter(ArrayList())

    @Provides
    fun provideLanguageViewModel(): LanguageViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(LanguageViewModel::class) {
                LanguageViewModel()
            })[LanguageViewModel::class.java]
    }

    @Provides
    fun provideLanguageSelectionAdapter() = LanguageSelectionAdapter(ArrayList())
}
