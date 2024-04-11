package com.app.crypto.presentation.coinlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.crypto.domain.usecase.GetCoinsUseCase

class CoinViewModel(var getCoinsUseCase: GetCoinsUseCase) : ViewModel() {
    fun getCoins() =
        liveData {
            val coinList = getCoinsUseCase.execute()
            emit(coinList)
        }
}