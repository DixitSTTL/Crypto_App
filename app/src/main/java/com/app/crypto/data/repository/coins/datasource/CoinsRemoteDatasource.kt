package com.app.crypto.data.repository.coins.datasource

import androidx.paging.PagingData
import com.app.crypto.data.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsRemoteDatasource {
    suspend fun getCoins(): Flow<PagingData<Coin>>
}