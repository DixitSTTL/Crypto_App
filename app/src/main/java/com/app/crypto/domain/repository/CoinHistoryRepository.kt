package com.app.crypto.domain.repository

import com.app.crypto.data.model.CoinHistoryData

interface CoinHistoryRepository {
    suspend fun getCoinHistory(end: String, timePeriod: String): CoinHistoryData?
}