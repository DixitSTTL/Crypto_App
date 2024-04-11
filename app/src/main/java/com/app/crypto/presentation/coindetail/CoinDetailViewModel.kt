package com.app.crypto.presentation.coindetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase

class CoinDetailViewModel(var getCoinsDetailUseCase: GetCoinsDetailUseCase) : ViewModel() {


    fun getCoinsDetail(COIN_ID: String) = liveData {
        val coinList = getCoinsDetailUseCase.execute(COIN_ID)
        emit(coinList)
    }


}