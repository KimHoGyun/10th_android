package com.example.myapplication.di

import com.example.myapplication.data.repository.UserRepositoryImpl
import com.example.myapplication.data.repository.WishlistRepositoryImpl
import com.example.myapplication.domain.repository.UserRepository
import com.example.myapplication.domain.repository.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWishlistRepository(impl: WishlistRepositoryImpl): WishlistRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
