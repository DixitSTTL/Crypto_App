package com.app.crypto.data.repository.coinhistory.datasource

import com.app.crypto.data.model.CoinHistory
import retrofit2.Response

interface CoinHistoryRemoteDatasource {
    suspend fun getCoinHistory(end: String, timePeriod: String): Response<CoinHistory>
}