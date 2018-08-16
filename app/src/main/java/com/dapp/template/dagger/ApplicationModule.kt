/*
 *
 *  * Copyright 2018 Mark Dappollone
 *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

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
    fun provideDuckDuckGoService(okHttpClient: OkHttpClient, moshi: Moshi) : DuckDuckGoService =
        DuckDuckGoService.Creator.create(application.resources.getString(R.string.duck_duck_go_api_url),
                okHttpClient, moshi)

    @Provides
    @Singleton
    fun provideApiClient(duckDuckGoService: DuckDuckGoService) : ApiClient = ApiClient(duckDuckGoService)

    @Provides
    @Singleton
    fun getSchedulers() : Schedulers = MySchedulers()

}