package com.prachi.newsappmvvmarch

import android.app.Application
import com.prachi.newsappmvvmarch.di.component.ApplicationComponent
import com.prachi.newsappmvvmarch.di.component.DaggerApplicationComponent
import com.prachi.newsappmvvmarch.di.module.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
        applicationComponent.inject(this)
    }
}
