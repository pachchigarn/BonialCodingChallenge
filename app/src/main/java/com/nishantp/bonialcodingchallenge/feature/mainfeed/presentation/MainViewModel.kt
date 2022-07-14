package com.nishantp.bonialcodingchallenge.feature.mainfeed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.model.Brochure
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.use_case.FeedUseCase
import com.nishantp.bonialcodingchallenge.util.DataState
import com.nishantp.bonialcodingchallenge.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val feedUseCase: FeedUseCase) : ViewModel() {

    private val _feedState = MutableLiveData(
        FeedState(
            brochures = emptyList(),
            isLoading = false,
            isError = false,
            error = null
        )
    )
    val feedState: LiveData<FeedState> = _feedState

    init {
        loadFeeds()
    }

    private fun loadFeeds() {
        _feedState.value = feedState.value?.copy(
            isLoading = true
        )

        viewModelScope.launch {
            feedUseCase.getFeed().onEach {
                when(it) {
                    is DataState.Success -> {
                        _feedState.value = feedState.value?.copy(
                            brochures = it.data,
                            isLoading = false,
                            isError = false
                        )
                    }

                    is DataState.ErrorHandler -> {
                        _feedState.value = feedState.value?.copy(
                            error = it.errorMessage,
                            isLoading = false,
                            isError = true
                        )
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}

data class FeedState(
    val brochures: List<Brochure>,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: UiText? = null
)