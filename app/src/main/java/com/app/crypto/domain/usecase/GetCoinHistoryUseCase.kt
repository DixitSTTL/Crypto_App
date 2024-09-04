package com.app.crypto.domain.usecase

import com.app.crypto.data.model.CoinHistoryData
import com.app.crypto.domain.repository.CoinHistoryRepository
import com.app.crypto.presentation.util.ResultResponse

class GetCoinHistoryUseCase(var coinHistoryRepository: CoinHistoryRepository) {
    suspend fun execute(end: String?, timePeriod: String): ResultResponse<CoinHistoryData?> =
        coinHistoryRepository.getCoinHistory(end, timePeriod)

}