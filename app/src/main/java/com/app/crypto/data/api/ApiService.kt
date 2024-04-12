package com.app.crypto.data.api

import com.app.crypto.data.model.CoinDetail
import com.app.crypto.data.model.CoinHistory
import com.app.crypto.data.model.Coins
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("coins")
    suspend fun getCoins(
        @Query("x-access-token") key: String,
        @Query("limit") limit: String
    ): Response<Coins>

    @GET()
    suspend fun getCoinDetail(
        @Url url: String,
        @Query("x-access-token") key: String
    ): Response<CoinDetail>

    @GET()
    suspend fun getCoinHistory(
        @Url url: String,
        @Query("timePeriod") timePeriod: String,
        @Query("x-access-token") key: String
    ): Response<CoinHistory>

}