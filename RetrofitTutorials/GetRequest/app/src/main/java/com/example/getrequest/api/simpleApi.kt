package com.example.getrequest.api

import com.example.getrequest.model.Post
import retrofit2.Response
import retrofit2.http.*

interface simpleApi {


    @Headers(
        "authorization: 123321321",
        "platform: IPs"
    )
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>

    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    @GET("posts")
    suspend fun getCustomPosts2(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>


    //@body used for request body
    //ПОСЫЛАЕТ json
    @POST("post")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>


    //@body used for request body
    //will send it like id = 1 & _sort ="id"...
    @POST("post")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body")  body: String
    ): Response<Post>

}