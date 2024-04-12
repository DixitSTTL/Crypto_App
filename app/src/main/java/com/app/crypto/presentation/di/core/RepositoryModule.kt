package com.app.crypto.presentation.di.core

import com.app.crypto.data.repository.coindetail.CoinsDetailRepositoryImpl
import com.app.crypto.data.repository.coindetail.datasource.CoinsDetailsRemoteDatasource
import com.app.crypto.data.repository.coinhistory.CoinHistoryRepositoryImpl
import com.app.crypto.data.repository.coinhistory.datasource.CoinHistoryRemoteDatasource
import com.app.crypto.data.repository.coins.CoinsRepositoryImpl
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import com.app.crypto.domain.repository.CoinHistoryRepository
import com.app.crypto.domain.repository.CoinsDetailRepository
import com.app.crypto.domain.repository.CoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCoinRepository(
        coinsDatasource: CoinsRemoteDatasource
    ): CoinsRepository {
        return CoinsRepositoryImpl(coinsDatasource)
    }


    @Provides
    @Singleton
    fun provideCoinDetailRepository(
        coinsDetailsRemoteDatasource: CoinsDetailsRemoteDatasource
    ): CoinsDetailRepository {
        return CoinsDetailRepositoryImpl(coinsDetailsRemoteDatasource)
    }

    @Provides
    @Singleton
    fun provideCoinHistoryRepository(
        coinHistoryRemoteDatasource: CoinHistoryRemoteDatasource
    ): CoinHistoryRepository {
        return CoinHistoryRepositoryImpl(coinHistoryRemoteDatasource)
    }

}