package com.app.crypto.data.repository.coindetail

import com.app.crypto.data.model.Coin_
import com.app.crypto.data.repository.coindetail.datasource.CoinsDetailsRemoteDatasource
import com.app.crypto.domain.repository.CoinsDetailRepository

class CoinsDetailRepositoryImpl(var coinsDatasource: CoinsDetailsRemoteDatasource) :
    CoinsDetailRepository {

    override suspend fun getCoinsDetail(end:String): Coin_? {
        val response = coinsDatasource.getCoinsDetail(end)
        val body = response.body()
        val dataCoin = body?.data?.coin


        return dataCoin
    }
}