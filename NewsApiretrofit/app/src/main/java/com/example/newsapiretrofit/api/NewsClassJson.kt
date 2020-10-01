package com.example.newsapiretrofit.api

data class NewsClassJson(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)