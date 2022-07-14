package com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.repository.FakeFeedRepository
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.model.Brochure
import com.nishantp.bonialcodingchallenge.util.DataState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFeedTest {

    private lateinit var getFeed: GetFeed
    private lateinit var fakeRepository: FakeFeedRepository

    @Before
    fun setUp() {
        fakeRepository = FakeFeedRepository()
        getFeed = GetFeed(fakeRepository)
    }

    @Test
    fun `Filter brochure from DTO`() = runBlocking {
        val listBrochure: DataState<List<Brochure>> = getFeed.invoke().first()

        assertThat(listBrochure is DataState.Success).isTrue()

        println("List: $listBrochure")
        (listBrochure as DataState.Success).data.forEach {
            assertThat(it.contentType.contentEquals("brochure") || it.contentType.contentEquals("brochurePremium")).isTrue()
        }
    }

    @Test
    fun `Filter brochure by distance less than 5 kms`() = runBlocking {
        val listBrochure: DataState<List<Brochure>> = getFeed.invoke().first()

        assertThat(listBrochure is DataState.Success).isTrue()

        (listBrochure as DataState.Success).data.forEach {
            assertThat(it.distance < 5).isTrue()
        }
    }

    @Test
    fun `Order feeds by distance`() = runBlocking {
        val listBrochure: DataState<List<Brochure>> = getFeed.invoke().first()

        assertThat(listBrochure is DataState.Success).isTrue()

        for (i in 0..(listBrochure as DataState.Success).data.size - 2) {
            assertThat(listBrochure.data[i].distance < listBrochure.data[i + 1].distance).isTrue()
        }
    }
}