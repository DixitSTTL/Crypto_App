package com.app.crypto.data.repository.coins

import androidx.paging.PagingData
import com.app.crypto.data.model.Coin
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import com.app.crypto.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class CoinsRepositoryImpl(var coinsDatasource: CoinsRemoteDatasource) : CoinsRepository {
    override suspend fun getCoins(): Flow<PagingData<Coin>> {
        return coinsDatasource.getCoins()


    }
}