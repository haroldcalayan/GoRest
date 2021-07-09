package com.haroldcalayan.gorest.di

import android.content.Context
import androidx.room.Room
import com.haroldcalayan.gorest.BuildConfig
import com.haroldcalayan.gorest.data.source.local.database.GoRestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): GoRestDatabase {
        return Room.databaseBuilder(
            appContext,
            GoRestDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideProductDao(database: GoRestDatabase) = database.productDao()

    @Provides
    fun provideProductCategoryDao(database: GoRestDatabase) = database.productCategoryDao()

    @Provides
    fun provideTodoDao(database: GoRestDatabase) = database.todoDao()

    @Provides
    fun provideUserDao(database: GoRestDatabase) = database.userDao()

}