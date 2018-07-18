package com.dapp.template

import com.dapp.template.model.SearchResults
import com.dapp.template.webservices.DuckDuckGoService
import io.reactivex.Observable

class ApiClient(private val duckDuckGoService: DuckDuckGoService) {
    fun search(query: String) : Observable<SearchResults> = duckDuckGoService.search(query = query)
}