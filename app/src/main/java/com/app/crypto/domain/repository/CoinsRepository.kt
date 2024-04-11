package com.app.crypto.domain.repository

import com.app.crypto.data.model.Coin

interface CoinsRepository {

    suspend fun getCoins(): List<Coin>
}