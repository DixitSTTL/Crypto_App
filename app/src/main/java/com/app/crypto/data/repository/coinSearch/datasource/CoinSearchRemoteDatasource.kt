package com.app.crypto.data.repository.coinSearch.datasource

import com.app.crypto.data.model.CoinSearch
import retrofit2.Response

interface CoinSearchRemoteDatasource {
    suspend fun getSearchCoin(end: String): Response<CoinSearch>
}