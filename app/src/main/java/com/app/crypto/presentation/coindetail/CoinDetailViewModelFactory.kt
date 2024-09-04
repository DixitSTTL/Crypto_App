package com.app.crypto.presentation.coindetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.crypto.domain.usecase.GetCoinHistoryUseCase
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase

class CoinDetailViewModelFactory(
    var getCoinsDetailUseCase: GetCoinsDetailUseCase,
    var getCoinHistoryUseCase: GetCoinHistoryUseCase,
    ) :
    ViewModelProvider.Factory {
    private var coinId: String?=null

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinDetailViewModel(getCoinsDetailUseCase, getCoinHistoryUseCase, coinId) as T
    }

    fun setCoinId(coinId: String) {
        this.coinId = coinId
    }
}