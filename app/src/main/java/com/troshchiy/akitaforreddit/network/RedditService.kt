package com.troshchiy.akitaforreddit.network

import com.troshchiy.akitaforreddit.network.data.TopNewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("top") suspend fun topNews(
        @Query("limit") limit: Int,
        @Query("after") after: String? = null
    ): Response<TopNewsDto>
}
