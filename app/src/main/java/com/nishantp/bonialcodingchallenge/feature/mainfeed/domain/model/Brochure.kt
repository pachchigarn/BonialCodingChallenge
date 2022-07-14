package com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.model

data class Brochure(
    val id: Int,
    val contentType: String,
    val imageUrl: String,
    val retailerName: String,
    val distance: Double
)