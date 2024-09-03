package com.app.crypto.data.repository.coins

import com.app.crypto.data.model.Coins
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import com.app.crypto.domain.repository.CoinsRepository
import retrofit2.Response

class CoinsRepositoryImpl(var coinsDatasource: CoinsRemoteDatasource) : CoinsRepository {
    override suspend fun getCoins(): Response<Coins> {
        val response = coinsDatasource.getCoins()
        return response

    }
}