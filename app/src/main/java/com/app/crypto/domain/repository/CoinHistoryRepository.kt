package com.app.crypto.domain.repository

import com.app.crypto.data.model.CoinHistoryData
import com.app.crypto.presentation.util.ResultResponse

interface CoinHistoryRepository {
    suspend fun getCoinHistory(end: String?, timePeriod: String): ResultResponse<CoinHistoryData?>
}