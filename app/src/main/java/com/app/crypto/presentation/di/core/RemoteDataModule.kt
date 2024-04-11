package com.app.crypto.presentation.di.core

import com.app.crypto.data.api.ApiService
import com.app.crypto.data.repository.coindetail.datasource.CoinsDetailsRemoteDatasource
import com.app.crypto.data.repository.coindetail.datasourceimpl.CoinsDetailRemoteDatasourceImpl
import com.app.crypto.data.repository.coins.datasource.CoinsRemoteDatasource
import com.app.crypto.data.repository.coins.datasourceimpl.CoinsRemoteDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule() {

    @Provides
    @Singleton
    fun provideCoinDataSource(apiService: ApiService): CoinsRemoteDatasource {
        return CoinsRemoteDatasourceImpl(
            apiService,
            "coinranking197d6c596b85998a5f498d684933a048fa5534a10ed2c856"
        )

    }

    @Provides
    @Singleton
    fun provideCoinDetailDataSource(apiService: ApiService): CoinsDetailsRemoteDatasource {
        return CoinsDetailRemoteDatasourceImpl(
            apiService,
            "coinranking197d6c596b85998a5f498d684933a048fa5534a10ed2c856"
        )

    }


}