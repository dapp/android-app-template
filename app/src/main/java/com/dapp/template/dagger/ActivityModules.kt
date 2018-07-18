package com.dapp.template.dagger

import android.content.Context
import com.dapp.template.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {
    @ActivityContext
    @Binds
    abstract fun provideContext(activity: MainActivity): Context
}