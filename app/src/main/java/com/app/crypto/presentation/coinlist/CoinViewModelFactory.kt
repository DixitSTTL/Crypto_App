package com.app.crypto.presentation.coinlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.crypto.domain.usecase.GetCoinsUseCase

class CoinViewModelFactory(var getCoinsUseCase: GetCoinsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinViewModel(getCoinsUseCase) as T
    }
}