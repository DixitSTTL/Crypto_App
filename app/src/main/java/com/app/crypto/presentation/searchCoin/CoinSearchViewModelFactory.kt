package com.app.crypto.presentation.searchCoin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.crypto.domain.usecase.GetSearchCoinUseCase

class CoinSearchViewModelFactory(var getSearchCoinUseCase: GetSearchCoinUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelSearch(getSearchCoinUseCase) as T
    }
}