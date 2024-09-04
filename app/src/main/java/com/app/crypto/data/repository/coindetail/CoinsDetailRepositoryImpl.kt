package com.app.crypto.data.repository.coindetail

import com.app.crypto.data.model.Coin_
import com.app.crypto.data.repository.coindetail.datasource.CoinsDetailsRemoteDatasource
import com.app.crypto.domain.repository.CoinsDetailRepository
import com.app.crypto.presentation.util.ResultResponse

class CoinsDetailRepositoryImpl(var coinsDatasource: CoinsDetailsRemoteDatasource) :
    CoinsDetailRepository {

    override suspend fun getCoinsDetail(end: String?): ResultResponse<Coin_?> {
        return try {
            if(end!=null) {
                val response = coinsDatasource.getCoinsDetail(end)
                if (response.isSuccessful) {
                    response.body()?.let {
                        ResultResponse.Success(it.data.coin)
                    } ?: ResultResponse.Error(Exception("No Data"))
                }
                else{
                    ResultResponse.Error(Exception("API Error: ${response.code()}"))
                }
            }
            else{
                ResultResponse.Error(Exception("coin id empty"))
            }

        }
        catch (e:Exception){
            return ResultResponse.Error(e)

        }
    }
}