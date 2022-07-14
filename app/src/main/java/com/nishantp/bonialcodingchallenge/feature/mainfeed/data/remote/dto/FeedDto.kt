package com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FeedDto(
    @SerializedName("_embedded")
    val embedded: Embedded,
    @SerializedName("_links")
    val links: Links,
    val page: PageDto
)