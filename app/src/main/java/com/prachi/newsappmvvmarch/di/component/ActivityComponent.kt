package com.prachi.newsappmvvmarch.di.component

import com.prachi.newsappmvvmarch.di.ActivityScope
import com.prachi.newsappmvvmarch.di.module.ActivityModule
import com.prachi.newsappmvvmarch.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity:TopHeadlineActivity)
}