package ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface infoDao {

    @GET("activity?")
    suspend fun getInfo(
        @Query("participants")
        participants: String
    ) : InfoAPI
}