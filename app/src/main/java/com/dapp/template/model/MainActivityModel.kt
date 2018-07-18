package com.dapp.template.model

import com.dapp.template.ApiClient
import io.reactivex.Observable
import javax.inject.Inject

class MainActivityModel @Inject constructor(private val apiClient: ApiClient) {
    fun loadData(query: String) : Observable<SearchResults> = apiClient.search(query)
}