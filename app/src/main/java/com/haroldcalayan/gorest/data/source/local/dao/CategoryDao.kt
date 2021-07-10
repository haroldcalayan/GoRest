package com.haroldcalayan.gorest.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.haroldcalayan.gorest.base.BaseDao
import com.haroldcalayan.gorest.data.model.Category

@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun all(): List<Category>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun get(id: Int): Category?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC LIMIT 1")
    suspend fun getFirst(): Category?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun nukeTable()

    companion object {
        const val TABLE_NAME = "category"
    }
}