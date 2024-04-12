package com.app.crypto.presentation.coindetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.app.crypto.data.model.CoinHistoryData
import com.app.crypto.domain.usecase.GetCoinHistoryUseCase
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase
import com.app.crypto.presentation.util.CoinHistoryTimeFrame
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    var getCoinsDetailUseCase: GetCoinsDetailUseCase,
    var getCoinHistoryUseCase: GetCoinHistoryUseCase
) : ViewModel() {


    val mutableHistoryData = MutableLiveData<CoinHistoryData?>()
    val mutableTimeFrame = MutableLiveData(CoinHistoryTimeFrame.H24)
    fun getCoinsDetail(COIN_ID: String) = liveData {
        val coinList = getCoinsDetailUseCase.execute(COIN_ID)
        emit(coinList)
    }

    fun getHistoryData(COIN_ID: String) {
        viewModelScope.launch {
            Log.d("bfbf", ":" + mutableTimeFrame.value!!.timePeriod)

            val coinList =
                getCoinHistoryUseCase.execute(COIN_ID, mutableTimeFrame.value!!.timePeriod)
            mutableHistoryData.postValue(coinList)
        }

    }


}