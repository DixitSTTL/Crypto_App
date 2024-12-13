package com.app.crypto.data.repository.coins.datasourceimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.crypto.data.api.ApiService
import com.app.crypto.data.model.Coin
import com.app.crypto.data.paging.CoinPagingSource
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import kotlinx.coroutines.flow.Flow

class CoinsRemoteDatasourceImpl(
    private val apiService: ApiService,
    private val apiKey: String
) : CoinsRemoteDatasource {
    override suspend fun getCoins(): Flow<PagingData<Coin>> {


//        return apiService.getCoins(apiKey, "10", count.toString())
        return Pager(
            config = PagingConfig(
                pageSize = 1, enablePlaceholders = false, initialLoadSize = 2
            ), pagingSourceFactory = {
                CoinPagingSource(apiService, apiKey)

            }, initialKey = 1
        ).flow
    }
}