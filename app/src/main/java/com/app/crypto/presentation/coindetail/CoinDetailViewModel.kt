package com.app.crypto.presentation.coindetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.crypto.data.model.CoinHistoryData
import com.app.crypto.data.model.Coin_
import com.app.crypto.domain.usecase.GetCoinHistoryUseCase
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase
import com.app.crypto.presentation.util.CoinHistoryTimeFrame
import com.app.crypto.presentation.util.ResultResponse
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    var getCoinsDetailUseCase: GetCoinsDetailUseCase,
    var getCoinHistoryUseCase: GetCoinHistoryUseCase,
    var coinId: String?
) : ViewModel() {

    val mutableHistoryData = MutableLiveData<CoinHistoryData?>()
    val mutableCoinList = MutableLiveData<Coin_?>()
    val mutableTimeFrame = MutableLiveData(CoinHistoryTimeFrame.H24)

    init {
        getCoinsDetail()
        getHistoryData()
    }


    private fun getCoinsDetail() {
        viewModelScope.launch {
            val coinDetail =
                getCoinsDetailUseCase.execute(coinId)

            when (coinDetail) {
                is ResultResponse.Error -> Log.d("Eroorr", "" + coinDetail.exception.message)
                is ResultResponse.Success -> mutableCoinList.postValue(coinDetail.data)

            }
        }
    }

    fun getHistoryData() {
        viewModelScope.launch {

            val coinHistoryData =
                getCoinHistoryUseCase.execute(coinId, mutableTimeFrame.value!!.timePeriod)
            when (coinHistoryData) {
                is ResultResponse.Error -> Log.d("Eroorr", "" + coinHistoryData.exception.message)
                is ResultResponse.Success -> mutableHistoryData.postValue(coinHistoryData.data)

            }
        }

    }


}