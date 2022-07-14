package com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote

import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto.FeedDto
import retrofit2.http.GET

interface FeedApi {

    @GET("stories-test/shelf.json")
    suspend fun getMainFeed(): FeedDto
}