package com.app.crypto.domain.usecase

import com.app.crypto.data.model.Coins
import com.app.crypto.domain.repository.CoinsRepository
import com.app.crypto.presentation.util.ResultResponse

class GetCoinsUseCase(var coinsRepository: CoinsRepository) {
    suspend fun execute(): ResultResponse<Coins> = coinsRepository.getCoins()

}