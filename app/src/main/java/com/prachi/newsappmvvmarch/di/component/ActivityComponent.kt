package com.prachi.newsappmvvmarch.di.component

import com.prachi.newsappmvvmarch.di.ActivityScope
import com.prachi.newsappmvvmarch.di.module.ActivityModule
import com.prachi.newsappmvvmarch.ui.country.CountryListActivity
import com.prachi.newsappmvvmarch.ui.language.LanguageSelectionActivity
import com.prachi.newsappmvvmarch.ui.newslist.NewsListActivity
import com.prachi.newsappmvvmarch.ui.newssource.NewsSourcesActivity
import com.prachi.newsappmvvmarch.ui.search.SearchActivity
import com.prachi.newsappmvvmarch.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)
    fun inject(activity: NewsSourcesActivity)
    fun inject(activity: NewsListActivity)
    fun inject(activity: CountryListActivity)
    fun inject(activity: LanguageSelectionActivity)
    fun inject(activity: SearchActivity)
}
