package com.app.crypto.domain.usecase

import com.app.crypto.data.model.Coin_
import com.app.crypto.domain.repository.CoinsDetailRepository
import com.app.crypto.presentation.util.ResultResponse
import javax.inject.Inject

class GetCoinsDetailUseCase @Inject constructor(private var coinsDetailRepository: CoinsDetailRepository) {
    suspend fun execute(end: String?): ResultResponse<Coin_?> =
        coinsDetailRepository.getCoinsDetail(end)

}