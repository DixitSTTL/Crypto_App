package com.app.crypto.domain.usecase

import com.app.crypto.data.model.CoinSearch
import com.app.crypto.data.model.Coin_
import com.app.crypto.domain.repository.CoinSearchRepository
import com.app.crypto.domain.repository.CoinsDetailRepository
import com.app.crypto.presentation.util.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchCoinUseCase @Inject constructor(private var coinSearchRepository: CoinSearchRepository) {
    suspend fun execute(end: String): Flow<ResultResponse<CoinSearch>> =
        coinSearchRepository.getSearchCoin(end)

}