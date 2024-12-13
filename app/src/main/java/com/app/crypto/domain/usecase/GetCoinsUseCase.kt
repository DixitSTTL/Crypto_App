package com.app.crypto.domain.usecase

import androidx.paging.PagingData
import com.app.crypto.data.model.Coin
import com.app.crypto.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class GetCoinsUseCase(private var coinsRepository: CoinsRepository) {
    suspend fun execute(): Flow<PagingData<Coin>> = coinsRepository.getCoins()

}