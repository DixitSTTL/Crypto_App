package com.app.crypto.domain.repository

import androidx.paging.PagingData
import com.app.crypto.data.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    suspend fun getCoins(): Flow<PagingData<Coin>>
}