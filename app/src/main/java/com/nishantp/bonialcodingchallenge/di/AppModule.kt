package com.nishantp.bonialcodingchallenge.di

import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.remote.FeedApi
import com.nishantp.bonialcodingchallenge.feature.mainfeed.data.repository.FeedRepositoryImpl
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.repository.FeedRepository
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.use_case.FeedUseCase
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.use_case.GetFeed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object {
        const val BASE_URL =
            "https://test-mobile-configuration-files.s3.eu-central-1.amazonaws.com/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
    }

    @Singleton
    @Provides
    fun provideFeedApiEndPoint(retrofit: Retrofit.Builder): FeedApi {
        return retrofit.build().create(FeedApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFeedRepository(feedApi: FeedApi): FeedRepository {
        return FeedRepositoryImpl(feedApi)
    }

    @Provides
    @Singleton
    fun provideFeedUseCases(repository: FeedRepository): FeedUseCase {
        return FeedUseCase(
            getFeed = GetFeed(repository)
        )
    }
}