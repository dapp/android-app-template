package com.dapp.template.webservices

import com.dapp.template.model.SearchResults
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DuckDuckGoService {
    //with any normal api you'd use @GET("API_PATH"), but since the duck-duck go api has no path, the only way to
    // call it is by giving it the @Url parameter
    @GET
    fun search(@Url url: String = "https://api.duckduckgo.com/",
               @Query("q") query: String, @Query("format") format: String = "json",
               @Query("pretty") pretty: Int = 1):
            Observable<SearchResults>

    object Creator {
        @JvmStatic
        fun create(baseUrl: String, client: OkHttpClient, moshi: Moshi): DuckDuckGoService {
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()

            return retrofit.create(DuckDuckGoService::class.java)
        }
    }
}


