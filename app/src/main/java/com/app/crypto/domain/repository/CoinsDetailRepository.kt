package com.app.crypto.domain.repository


import com.app.crypto.data.model.Coin_
import com.app.crypto.presentation.util.ResultResponse

interface CoinsDetailRepository {

    suspend fun getCoinsDetail(end: String?): ResultResponse<Coin_?>
}