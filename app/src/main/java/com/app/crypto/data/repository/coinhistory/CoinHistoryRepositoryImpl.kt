package com.app.crypto.data.repository.coinhistory

import com.app.crypto.data.model.CoinHistoryData
import com.app.crypto.data.repository.coinhistory.datasource.CoinHistoryRemoteDatasource
import com.app.crypto.domain.repository.CoinHistoryRepository
import com.app.crypto.presentation.util.ResultResponse

class CoinHistoryRepositoryImpl(var coinHistoryRemoteDatasource: CoinHistoryRemoteDatasource) :
    CoinHistoryRepository {

    override suspend fun getCoinHistory(
        end: String?,
        timePeriod: String
    ): ResultResponse<CoinHistoryData?> {
        return try {
            if (end != null) {
                val response = coinHistoryRemoteDatasource.getCoinHistory(end, timePeriod)
                if (response.isSuccessful) {
                    response.body()?.let {
                        ResultResponse.Success(it.data)
                    } ?: ResultResponse.Error(Exception("No Data"))
                } else {
                    ResultResponse.Error(Exception("API Error: ${response.code()}"))
                }
            } else {
                ResultResponse.Error(Exception("coin id empty"))
            }

        } catch (e: Exception) {
            ResultResponse.Error(e)
        }
    }
}