package com.app.crypto.presentation.coinlist

import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.crypto.data.model.Coin
import com.app.crypto.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.launch

class CoinViewModel(var getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    val coinsState = MutableLiveData<CoinsState>()

    init {
        getCoins()
    }

    private fun getCoins() =

        viewModelScope.launch() {
            coinsState.value = CoinsState.Loading
            val response = getCoinsUseCase.execute()
            try {
                val body = response.body()
                Log.d("TAG", "getCoins: ${response.raw()}")

                if (body != null) {
                    coinsState.value = CoinsState.Success(body.data.coins)

                } else {
                    coinsState.value = CoinsState.Error("" + response.body())

                }


            } catch (exception: Exception) {
                Log.i("MyTag", exception.message.toString())
                coinsState.value = CoinsState.Error(exception.message.toString() ?: "Unknown Error")

            }

        }


}

sealed class CoinsState {
    object Loading : CoinsState()
    data class Success(val coins: List<Coin>) : CoinsState()
    data class Error(val message: String) : CoinsState()
}