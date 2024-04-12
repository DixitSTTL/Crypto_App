package com.app.crypto.presentation.di.core

import com.app.crypto.domain.usecase.GetCoinHistoryUseCase
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase
import com.app.crypto.domain.usecase.GetCoinsUseCase
import com.app.crypto.presentation.coindetail.CoinDetailViewModelFactory
import com.app.crypto.presentation.coinlist.CoinViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideCoinViewModelFactory(
        getCoinsUseCase: GetCoinsUseCase
    ): CoinViewModelFactory {
        return CoinViewModelFactory(
            getCoinsUseCase,
        )
    }

    @Provides
    @Singleton
    fun provideCoinDetailViewModelFactory(
        getCoinsDetailUseCase: GetCoinsDetailUseCase,
        getCoinHistoryUseCase: GetCoinHistoryUseCase
    ): CoinDetailViewModelFactory {
        return CoinDetailViewModelFactory(
            getCoinsDetailUseCase,
            getCoinHistoryUseCase
        )
    }


}