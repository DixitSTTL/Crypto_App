package com.app.crypto.data.repository.coindetail.datasourceimpl

import com.app.crypto.data.api.ApiService
import com.app.crypto.data.model.CoinDetail
import com.app.crypto.data.repository.coindetail.datasource.CoinsDetailsRemoteDatasource
import retrofit2.Response

class CoinsDetailRemoteDatasourceImpl(
    private val apiService: ApiService,
    private val apiKey: String
) : CoinsDetailsRemoteDatasource {
    override suspend fun getCoinsDetail(end:String): Response<CoinDetail> =apiService.getCoinDetail("coin/"+end,apiKey)
}