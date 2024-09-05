package com.app.crypto.domain.repository

import com.app.crypto.data.model.Coins
import com.app.crypto.presentation.util.ResultResponse

interface CoinsRepository {

    suspend fun getCoins(): ResultResponse<Coins>
}