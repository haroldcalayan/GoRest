package com.haroldcalayan.gorest.di

import com.haroldcalayan.gorest.data.repository.ProductRepository
import com.haroldcalayan.gorest.data.repository.ProductRepositoryImpl
import com.haroldcalayan.gorest.data.repository.UserRepository
import com.haroldcalayan.gorest.data.repository.UserRepositoryImpl
import com.haroldcalayan.gorest.data.source.local.database.GoRestDatabase
import com.haroldcalayan.gorest.data.source.remote.service.GoRestApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(database: GoRestDatabase, api: GoRestApiService): ProductRepository {
        return ProductRepositoryImpl(database, api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: GoRestDatabase, api: GoRestApiService): UserRepository {
        return UserRepositoryImpl(database, api)
    }
}