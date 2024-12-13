package com.app.crypto.data.repository.coinSearch

import com.app.crypto.data.model.CoinSearch
import com.app.crypto.data.repository.coinSearch.datasource.CoinSearchRemoteDatasource
import com.app.crypto.domain.repository.CoinSearchRepository
import com.app.crypto.presentation.util.ResultResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinSearchRepositoryImpl(private var coinSearchRemoteDatasourceImpl: CoinSearchRemoteDatasource) :
    CoinSearchRepository {


    override suspend fun getSearchCoin(str: String): Flow<ResultResponse<CoinSearch>> {
        return flow {
            try {
                run {
                    ResultResponse.Loading

                    val response = coinSearchRemoteDatasourceImpl.getSearchCoin(str)
                    if (response.isSuccessful) {
                        delay(5000)
                        response.body()?.let {
                            ResultResponse.Success(it)
                        } ?: ResultResponse.Error(Exception("No Data"))
                    } else {
                        ResultResponse.Error(Exception("API Error: ${response.code()}"))
                    }
                }

            } catch (e: Exception) {
                ResultResponse.Error(e)
            }

        }
    }

}