package com.app.crypto.presentation.coinlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.crypto.data.model.Coin
import com.app.crypto.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.launch

class CoinViewModel(private var getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _coinsState = MutableLiveData<CoinsState>()
    val coinsState: LiveData<CoinsState> get() = _coinsState

    init {
        getCoins()
    }

    private fun getCoins() {

        viewModelScope.launch {
            setLoading()
            val response = getCoinsUseCase.execute().cachedIn(viewModelScope)
            try {
                response.collect { it ->
                    _coinsState.postValue(CoinsState.Success(it))
                }

            } catch (exception: Exception) {
                Log.i("MyTag", exception.message.toString())
                _coinsState.value =
                    CoinsState.Error(exception.message.toString() ?: "Unknown Error")

            }
        }

    }

    private fun setLoading() {
        _coinsState.value = CoinsState.Loading
    }


}

sealed class CoinsState {
    object Loading : CoinsState()
    data class Success(val coins: PagingData<Coin>) : CoinsState()
    data class Error(val message: String) : CoinsState()
}