package com.app.crypto.data.repository.coins.datasource

import com.app.crypto.data.model.Coins
import retrofit2.Response

interface CoinsRemoteDatasource {
    suspend fun getCoins(): Response<Coins>
}