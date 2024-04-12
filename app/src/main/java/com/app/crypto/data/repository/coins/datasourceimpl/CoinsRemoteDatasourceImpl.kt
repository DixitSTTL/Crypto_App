package com.app.crypto.data.repository.coins.datasourceimpl

import com.app.crypto.data.api.ApiService
import com.app.crypto.data.model.Coins
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import retrofit2.Response

class CoinsRemoteDatasourceImpl(
    private val apiService: ApiService,
    private val apiKey: String
) : CoinsRemoteDatasource {
    override suspend fun getCoins(): Response<Coins> = apiService.getCoins(apiKey, "100")
}