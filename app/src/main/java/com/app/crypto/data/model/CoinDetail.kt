package com.app.crypto.data.model

data class CoinDetail(
    val `data`: _Data,
    val status: String
)

data class _Data(
    val coin: Coin_
)

data class Coin_(
    val `24hVolume`: String,
    val allTimeHigh: AllTimeHigh,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val contractAddresses: List<Any>,
    val description: String,
    val fullyDilutedMarketCap: String,
    val hasContent: Boolean,
    val iconUrl: String,
    val links: List<Link>,
    val listedAt: Int,
    val lowVolume: Boolean,
    val marketCap: String,
    val name: String,
    val notices: Any,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val supply: Supply,
    val symbol: String,
    val tags: List<String>,
    val tier: Int,
    val uuid: String,
    val websiteUrl: String
)

data class AllTimeHigh(
    val price: String,
    val timestamp: Int
)

data class Link(
    val name: String,
    val type: String,
    val url: String
)

data class Supply(
    val circulating: String,
    val confirmed: Boolean,
    val max: Any,
    val supplyAt: Int,
    val total: String
)