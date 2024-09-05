package com.app.crypto.data.repository.coins

import com.app.crypto.data.model.Coins
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import com.app.crypto.domain.repository.CoinsRepository
import com.app.crypto.presentation.util.ResultResponse

class CoinsRepositoryImpl(var coinsDatasource: CoinsRemoteDatasource) : CoinsRepository {
    override suspend fun getCoins(): ResultResponse<Coins> {
        return try {
            val response = coinsDatasource.getCoins()
            if (response.isSuccessful) {
                response.body()?.let {
                    ResultResponse.Success(it)
                } ?: ResultResponse.Error(Exception("No Data"))
            } else {
                ResultResponse.Error(Exception("API Error: ${response.code()}"))
            }


        } catch (e: Exception) {
            ResultResponse.Error(e)

        }


    }
}