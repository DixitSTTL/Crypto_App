package com.app.crypto.domain.repository


import com.app.crypto.data.model.CoinSearch
import com.app.crypto.presentation.util.ResultResponse
import kotlinx.coroutines.flow.Flow

interface CoinSearchRepository {

    suspend fun getSearchCoin(str: String): Flow<ResultResponse<CoinSearch>>
}