package com.app.crypto.presentation.util

enum class CoinHistoryTimeFrame(val timePeriod: String) {
    H1("1h"),
    H3("3h"),
    H12("12h"),
    H24("24h"),
    D7("7d"),
    D30("30d"),
    M3("3m"),
    Y1("1y"),
    Y3("3y"),
    Y5("5y")
}