package com.app.crypto.domain.repository

import com.app.crypto.data.model.Coins
import retrofit2.Response

interface CoinsRepository {

    suspend fun getCoins(): Response<Coins>
}