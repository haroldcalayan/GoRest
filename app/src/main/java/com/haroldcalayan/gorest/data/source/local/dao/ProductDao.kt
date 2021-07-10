package com.haroldcalayan.gorest.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.haroldcalayan.gorest.base.BaseDao
import com.haroldcalayan.gorest.data.model.Product

@Dao
interface ProductDao : BaseDao<Product> {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun all(): List<Product>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun get(id: Int): Product?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC LIMIT 1")
    suspend fun getFirst(): Product?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun nukeTable()

    companion object {
        const val TABLE_NAME = "product"
    }
}