package com.nishantp.bonialcodingchallenge.feature.mainfeed.data.mapper


import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto.Embedded
import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto.FeedDto
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.model.Brochure

fun FeedDto.toBrochures(): List<Brochure> {
    return embedded.toBrochures()
}

private fun Embedded.toBrochures(): List<Brochure> {
    var brochures: ArrayList<Brochure> = arrayListOf()

    contents.map {
        if (it.contentType.equals(
                "brochure", true
            ) ||
            it.contentType.equals(
                "brochurePremium", true
            )
        ) {
            it.content.map { it1 ->
                brochures.add(
                    Brochure(
                        id = it1.id,
                        contentType = it.contentType,
                        imageUrl = it1.brochureImage,
                        retailerName = it1.retailer?.name ?: "",
                        distance = it1.distance
                    )
                )
            }
        }
    }
    return brochures
}