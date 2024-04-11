package com.app.crypto.domain.usecase

import com.app.crypto.data.model.Coin
import com.app.crypto.domain.repository.CoinsRepository

class GetCoinsUseCase(var coinsRepository: CoinsRepository) {
    suspend fun execute(): List<Coin> = coinsRepository.getCoins()

}