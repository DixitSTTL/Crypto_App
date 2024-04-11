package com.app.crypto.domain.usecase

import com.app.crypto.data.model.Coin_
import com.app.crypto.domain.repository.CoinsDetailRepository

class GetCoinsDetailUseCase(var coinsDetailRepository: CoinsDetailRepository) {
    suspend fun execute( end: String): Coin_? = coinsDetailRepository.getCoinsDetail(end)

}