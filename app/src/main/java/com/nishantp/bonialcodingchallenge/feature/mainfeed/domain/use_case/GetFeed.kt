package com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.use_case


import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.model.Brochure
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.repository.FeedRepository
import com.nishantp.bonialcodingchallenge.util.DataState
import com.nishantp.bonialcodingchallenge.util.UiText
import com.nishantp.bonialcodingchallenge.R
import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.mapper.toBrochures
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFeed(private val repository: FeedRepository) {

    suspend operator fun invoke(): Flow<DataState<List<Brochure>>> = flow {
        when (val dataState = repository.getMainFeed()) {
            is DataState.Success -> {
                val filteredList =
                    dataState.data.toBrochures().filter { it.distance < 5 }.sortedBy { it.distance }
                emit(DataState.Success(filteredList))
            }

            is DataState.ErrorHandler -> {
                emit(
                    DataState.ErrorHandler(
                        exception = dataState.exception,
                        errorMessage = UiText.StringResource(resId = R.string.unknown_error)
                    )
                )
            }
        }
    }
}