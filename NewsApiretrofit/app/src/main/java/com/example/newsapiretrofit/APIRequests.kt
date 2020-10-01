package com.example.newsapiretrofit

import com.example.newsapiretrofit.api.NewsClassJson
import retrofit2.http.GET

interface APIRequests {

    @GET("v2/everything?q=apple&sortBy=popularity&apiKey=e9dc0e47b98445a382723d65319ee4f1")
    suspend fun getNews(): NewsClassJson
}