package com.app.crypto.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.crypto.data.api.ApiService
import com.app.crypto.data.model.Coin
import kotlinx.coroutines.delay


class CoinPagingSource(private var apiService: ApiService, private val apiKey: String) :
    PagingSource<Int, Coin>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getCoins(apiKey, "20", position.toString())

            delay(3000)

            LoadResult.Page(
                data = response.body()!!.data.coins,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}