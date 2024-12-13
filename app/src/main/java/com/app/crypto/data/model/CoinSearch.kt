package com.app.crypto.data.model

data class CoinSearch(
    val `data`: Data,
    val status: String
)

data class Data(
    val categories: List<Category>,
    val coins: List<Coin>,
    val exchanges: List<Any>,
    val fiat: List<Any>,
    val markets: List<Market>,
    val stats: Stats

)

data class Category(
    val iconUrl: String,
    val name: String,
    val numberOfCoins: Int,
    val shortname: String,
    val slug: String
)

data class Market(
    val baseSymbol: String,
    val baseUuid: String,
    val exchangeIconUrl: String,
    val exchangeName: String,
    val exchangeUuid: String,
    val quoteSymbol: String,
    val quoteUuid: String,
    val recommended: Boolean,
    val uuid: String
)