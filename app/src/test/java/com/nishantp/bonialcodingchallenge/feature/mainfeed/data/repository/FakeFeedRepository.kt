package com.nishantp.bonialcodingchallenge.feature.mainfeed.data.repository

import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.dto.*
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.repository.FeedRepository
import com.nishantp.bonialcodingchallenge.util.DataState
import org.junit.Before
import kotlin.random.Random

class FakeFeedRepository : FeedRepository {

    private fun getFakeListDto(): FeedDto {
        return FeedDto(embedded = getFakeEmbedded(), getFakeLinks(), getPageDto())
    }

    private fun getFakeLinks(): Links {
        return Links(self = Self(""))
    }

    private fun getPageDto(): PageDto {
        return PageDto(size = 0, totalElements = 1, totalPages = 2, number = 0)
    }

    private fun getFakeEmbedded(): Embedded {
        return Embedded(contents = getFakeListContent())
    }

    private fun getFakeListContent(): List<Content> {
        return (1..50).map {
            Content(placement = "", adFormat = "", contentType = getContentType(), content = getFakeListContentX())
        }
    }

    private fun getFakeListContentX(): List<ContentX> {
        return (1..5).map {
            ContentX(
                id = Random.nextInt(),
                brochureImage = "",
                distance = Random.nextDouble(0.1, 20.0),
                retailer = getFakeRetailer()
            )
        }
    }

    private fun getFakeRetailer(): Retailer {
        return Retailer(id = Random.nextInt(), name = "Fake Retailer Name")
    }

    private fun getContentType(): String {
        return if (Random.nextBoolean()) {
            if (Random.nextBoolean()) {
                "brochure"
            } else {
                "brochurePremium"
            }
        } else {
            "anyRandomContent"
        }
    }

    @Before
    fun setUp() {

    }

    override suspend fun getMainFeed(): DataState<FeedDto> {
        return DataState.Success(getFakeListDto())
    }

}