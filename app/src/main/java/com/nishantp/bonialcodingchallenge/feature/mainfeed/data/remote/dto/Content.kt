package com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto

import com.google.gson.annotations.JsonAdapter

data class Content(
    val placement: String,
    val adFormat: String,
    val contentType: String,
    @JsonAdapter(CollectionItemDeserializer::class)
    var content: List<ContentX>
)
