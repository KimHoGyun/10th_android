package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.local.WishlistDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideWishlistDataStore(@ApplicationContext context: Context): WishlistDataStore =
        WishlistDataStore(context)
}
