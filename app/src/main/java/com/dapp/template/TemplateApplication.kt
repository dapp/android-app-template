package com.dapp.template

import android.app.Activity
import android.app.Application
import com.dapp.howstheweather.BuildConfig
import com.dapp.template.dagger.ApplicationModule
import com.dapp.howstheweather.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import timber.log.Timber
import timber.log.Timber.DebugTree

class TemplateApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
                .inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}