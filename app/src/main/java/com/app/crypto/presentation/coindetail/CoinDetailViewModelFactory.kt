package com.app.crypto.presentation.coindetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase

class CoinDetailViewModelFactory(var getCoinsDetailUseCase: GetCoinsDetailUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinDetailViewModel(getCoinsDetailUseCase) as T
    }
}