package com.app.crypto.data.repository.coindetail.datasource

import com.app.crypto.data.model.CoinDetail
import retrofit2.Response

interface CoinsDetailsRemoteDatasource {
    suspend fun getCoinsDetail(end: String): Response<CoinDetail>
}