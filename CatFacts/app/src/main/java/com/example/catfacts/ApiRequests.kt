package com.example.catfacts

import com.example.catfacts.api.catsJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET("/facts/random")
    fun getCatFacts(): Call<catsJson>
}