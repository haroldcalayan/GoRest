package com.haroldcalayan.gorest.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haroldcalayan.gorest.data.model.*
import com.haroldcalayan.gorest.data.source.local.converter.CategoryConverter
import com.haroldcalayan.gorest.data.source.local.dao.*

const val VERSION_NUMBER = 1

@Database(
    entities = [Category::class, Product::class, ProductCategory::class, Todo::class, User::class],
    version = VERSION_NUMBER
)
@TypeConverters(CategoryConverter::class)
abstract class GoRestDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun productCategoryDao(): ProductCategoryDao
    abstract fun todoDao(): TodoDao
    abstract fun userDao(): UserDao
}