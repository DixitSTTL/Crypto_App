package com.app.crypto.data.repository.coinSearch.datasourceimpl

import com.app.crypto.data.api.ApiService
import com.app.crypto.data.model.CoinSearch
import com.app.crypto.data.repository.coinSearch.datasource.CoinSearchRemoteDatasource
import retrofit2.Response

class CoinSearchRemoteDatasourceImpl(
    private val apiService: ApiService,
) : CoinSearchRemoteDatasource {

    override suspend fun getSearchCoin(query: String): Response<CoinSearch> {
        return apiService.getCoinSearch("/v2/search-suggestions", query)
    }
}