package com.app.crypto.domain.usecase

import com.app.crypto.data.model.Coins
import com.app.crypto.domain.repository.CoinsRepository
import retrofit2.Response

class GetCoinsUseCase(var coinsRepository: CoinsRepository) {
    suspend fun execute(): Response<Coins> = coinsRepository.getCoins()

}