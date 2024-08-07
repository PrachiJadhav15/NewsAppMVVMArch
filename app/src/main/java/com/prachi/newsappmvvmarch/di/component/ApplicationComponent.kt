package com.prachi.newsappmvvmarch.di.component

import android.content.Context
import com.prachi.newsappmvvmarch.NewsApplication
import com.prachi.newsappmvvmarch.data.api.NetworkService
import com.prachi.newsappmvvmarch.data.repository.CountriesRepository
import com.prachi.newsappmvvmarch.data.repository.LanguagesRepository
import com.prachi.newsappmvvmarch.data.repository.NewsListRepository
import com.prachi.newsappmvvmarch.data.repository.NewsSourceRepository
import com.prachi.newsappmvvmarch.data.repository.SearchRepository
import com.prachi.newsappmvvmarch.data.repository.TopHeadlineRepository
import com.prachi.newsappmvvmarch.di.ApplicationContext
import com.prachi.newsappmvvmarch.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getNewsSourcesRepository(): NewsSourceRepository

    fun getNewsListRepository(): NewsListRepository

    fun getCountriesRepository(): CountriesRepository

    fun getLanguageRepository() : LanguagesRepository

    fun getSearchRepository(): SearchRepository
}
