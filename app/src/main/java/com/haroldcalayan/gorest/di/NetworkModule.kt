package com.haroldcalayan.gorest.di

import com.haroldcalayan.gorest.BuildConfig
import com.haroldcalayan.gorest.data.source.remote.interceptor.ApiInterceptor
import com.haroldcalayan.gorest.data.source.remote.service.GoRestApiService
import com.haroldcalayan.gorest.util.provideApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpClient(okHttpBuilder: OkHttpClient.Builder) = okHttpBuilder.build()

    @Provides
    fun provideHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(apiInterceptor)
        }
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    fun provideApiInterceptor() = ApiInterceptor()

    @Provides
    fun provideGithubApi(client: OkHttpClient): GoRestApiService {
        return provideApi(BuildConfig.BASE_GOREST_API_URL, client)
    }

}