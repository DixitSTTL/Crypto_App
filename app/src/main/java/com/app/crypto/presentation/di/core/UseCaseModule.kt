package com.app.crypto.presentation.di.core

import com.app.crypto.domain.repository.CoinHistoryRepository
import com.app.crypto.domain.repository.CoinsDetailRepository
import com.app.crypto.domain.repository.CoinsRepository
import com.app.crypto.domain.usecase.GetCoinHistoryUseCase
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase
import com.app.crypto.domain.usecase.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCoinsUseCase(coinsRepository: CoinsRepository): GetCoinsUseCase {
        return GetCoinsUseCase(coinsRepository)
    }


    @Provides
    @Singleton
    fun provideGetCoinsDetailUseCase(coinsDetailRepository: CoinsDetailRepository): GetCoinsDetailUseCase {
        return GetCoinsDetailUseCase(coinsDetailRepository)
    }

    @Provides
    @Singleton
    fun provideGetCoinHistoryUseCase(coinsHistoryRepository: CoinHistoryRepository): GetCoinHistoryUseCase {
        return GetCoinHistoryUseCase(coinsHistoryRepository)
    }

}