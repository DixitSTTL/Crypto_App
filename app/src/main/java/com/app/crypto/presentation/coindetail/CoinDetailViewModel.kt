package com.app.crypto.presentation.coindetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.crypto.data.model.CoinHistoryData
import com.app.crypto.data.model.Coin_
import com.app.crypto.domain.usecase.GetCoinHistoryUseCase
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase
import com.app.crypto.presentation.util.CoinHistoryTimeFrame
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    var getCoinsDetailUseCase: GetCoinsDetailUseCase,
    var getCoinHistoryUseCase: GetCoinHistoryUseCase,
    var coinId: String
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
            val coinList = getCoinsDetailUseCase.execute(coinId)
            mutableCoinList.postValue(coinList)

        }
    }

    fun getHistoryData() {
        viewModelScope.launch {

            val coinList =
                getCoinHistoryUseCase.execute(coinId, mutableTimeFrame.value!!.timePeriod)
            mutableHistoryData.postValue(coinList)
        }

    }


}