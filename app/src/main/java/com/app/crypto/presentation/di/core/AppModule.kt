package com.app.crypto.presentation.di.core

import android.content.Context
import com.app.crypto.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): MyApp {
        return context as MyApp

    }
}