package com.app.crypto.domain.repository


import com.app.crypto.data.model.Coin_

interface CoinsDetailRepository {

    suspend fun getCoinsDetail(end: String): Coin_?
}