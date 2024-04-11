package com.app.crypto.data.repository.coins

import android.util.Log
import com.app.crypto.data.model.Coin
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import com.app.crypto.domain.repository.CoinsRepository

class CoinsRepositoryImpl(var coinsDatasource: CoinsRemoteDatasource) : CoinsRepository {
    override suspend fun getCoins(): List<Coin> {
        lateinit var movieList: List<Coin>
        try {
            val response = coinsDatasource.getCoins()
            val body = response.body()
            if (body != null) {
                movieList = body.data.coins
            }
        } catch (exception: Exception) {
            movieList = ArrayList<Coin>()
            Log.i("MyTag", exception.message.toString())
        }
        return movieList

    }
}