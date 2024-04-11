package com.app.crypto.presentation.di.core

import com.app.crypto.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule() {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.coinranking.com/v2/")
            .client(builder.build())
            .build()
    }


    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit): ApiService {

        return retrofit.create(ApiService::class.java)

    }
}