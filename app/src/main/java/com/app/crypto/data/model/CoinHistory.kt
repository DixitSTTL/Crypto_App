package com.app.crypto.data.model

data class CoinHistory(
    val `data`: CoinHistoryData,
    val status: String
)

data class CoinHistoryData(
    val change: String,
    val history: List<History>
)

data class History(
    val price: String?,
    val timestamp: Int
)