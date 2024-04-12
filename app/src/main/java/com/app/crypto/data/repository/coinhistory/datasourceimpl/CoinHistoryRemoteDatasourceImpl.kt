package com.app.crypto.data.repository.coinhistory.datasourceimpl

import com.app.crypto.data.api.ApiService
import com.app.crypto.data.model.CoinHistory
import com.app.crypto.data.repository.coinhistory.datasource.CoinHistoryRemoteDatasource
import retrofit2.Response

class CoinHistoryRemoteDatasourceImpl(
    private val apiService: ApiService,
    private val apiKey: String
) : CoinHistoryRemoteDatasource {
    override suspend fun getCoinHistory(end: String, timePeriod: String): Response<CoinHistory> =
        apiService.getCoinHistory("coin/$end/history", timePeriod, apiKey)
}