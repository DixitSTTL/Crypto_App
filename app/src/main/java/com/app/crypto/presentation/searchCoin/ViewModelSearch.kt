package com.app.crypto.presentation.searchCoin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.crypto.data.model.CoinSearch
import com.app.crypto.domain.usecase.GetSearchCoinUseCase
import com.app.crypto.presentation.util.ResultResponse
import kotlinx.coroutines.launch

class ViewModelSearch(private val getSearchCoinUseCase: GetSearchCoinUseCase) :ViewModel(){

    private var _searchCoin = MutableLiveData<CoinSearch>()
    val searchCoin : LiveData<CoinSearch> = _searchCoin

    fun searchCoin(query: String) {

        viewModelScope.launch {

            getSearchCoinUseCase.execute(query).collect(){

                when(it){
                    is ResultResponse.Loading -> {
                        searchCoin.value
                    }
                    is ResultResponse.Error -> TODO()
                    is ResultResponse.Success -> {
                        _searchCoin.value = it.data

                    }
                }
            }

        }

    }


}