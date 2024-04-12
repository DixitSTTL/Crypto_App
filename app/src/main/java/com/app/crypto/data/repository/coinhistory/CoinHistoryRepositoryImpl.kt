package com.app.crypto.data.repository.coinhistory

import com.app.crypto.data.model.CoinHistoryData
import com.app.crypto.data.repository.coinhistory.datasource.CoinHistoryRemoteDatasource
import com.app.crypto.domain.repository.CoinHistoryRepository

class CoinHistoryRepositoryImpl(var coinHistoryRemoteDatasource: CoinHistoryRemoteDatasource) :
    CoinHistoryRepository {

    override suspend fun getCoinHistory(end: String, timePeriod: String): CoinHistoryData? {
        val response = coinHistoryRemoteDatasource.getCoinHistory(end, timePeriod)
        val body = response.body()
        val dataCoinHistory = body?.data


        return dataCoinHistory
    }
}