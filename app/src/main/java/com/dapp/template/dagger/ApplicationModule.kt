package com.dapp.template.dagger

import android.app.Application
import android.content.Context
import com.dapp.template.ApiClient
import com.dapp.template.MainActivity
import com.dapp.template.R
import com.dapp.template.utils.MySchedulers
import com.dapp.template.utils.Schedulers
import com.dapp.template.webservices.DuckDuckGoService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import org.jetbrains.annotations.Async
import javax.inject.Singleton


@Module
abstract class InjectorsModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

    @Binds
    @ApplicationContext
    abstract fun context(application: Application): Context
}

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideOpenWeatherMapService(okHttpClient: OkHttpClient, moshi: Moshi) : DuckDuckGoService =
        DuckDuckGoService.Creator.create(application.resources.getString(R.string.duck_duck_go_api_url),
                okHttpClient, moshi)

    @Provides
    @Singleton
    fun provideApiClient(duckDuckGoService: DuckDuckGoService) : ApiClient = ApiClient(duckDuckGoService)

    @Provides
    @Singleton
    fun getSchedulers() : Schedulers = MySchedulers()

}