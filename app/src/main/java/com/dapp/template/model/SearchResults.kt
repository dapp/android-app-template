package com.dapp.template.model

import com.squareup.moshi.Json

data class SearchResults(@Json(name = "RelatedTopics") val relatedTopics: List<RelatedTopic>)

data class RelatedTopic(@Json(name = "Text") val text: String?)