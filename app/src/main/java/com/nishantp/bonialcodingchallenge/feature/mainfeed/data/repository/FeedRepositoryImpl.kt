package com.nishantp.bonialcodingchallenge.feature.mainfeed.data.repository

import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.FeedApi
import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto.FeedDto
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.repository.FeedRepository
import com.nishantp.bonialcodingchallenge.util.DataState
import com.nishantp.bonialcodingchallenge.util.UiText

class FeedRepositoryImpl(private val feedApi: FeedApi) : FeedRepository {

    override suspend fun getMainFeed(): DataState<FeedDto> {
        return try {
            DataState.Success(feedApi.getMainFeed())
        } catch (e: Exception) {
            DataState.ErrorHandler(e, errorMessage = UiText.DynamicString(e.message ?: "Unknown Error"))
        }
    }
}