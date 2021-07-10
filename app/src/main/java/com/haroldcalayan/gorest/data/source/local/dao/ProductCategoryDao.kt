package com.haroldcalayan.gorest.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.haroldcalayan.gorest.base.BaseDao
import com.haroldcalayan.gorest.data.model.ProductCategory

@Dao
interface ProductCategoryDao : BaseDao<ProductCategory> {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun all(): List<ProductCategory>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun get(id: Int): ProductCategory?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC LIMIT 1")
    suspend fun getFirst(): ProductCategory?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun nukeTable()

    companion object {
        const val TABLE_NAME = "product_category"
    }
}