package com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.repository

import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto.FeedDto
import com.nishantp.bonialcodingchallenge.util.DataState

interface FeedRepository {

    suspend fun getMainFeed(): DataState<FeedDto>
}