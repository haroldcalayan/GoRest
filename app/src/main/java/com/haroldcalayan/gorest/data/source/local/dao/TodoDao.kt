package com.haroldcalayan.gorest.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.haroldcalayan.gorest.data.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun all(): List<Todo>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun get(id: Int): Todo?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC LIMIT 1")
    suspend fun getFirst(): Todo?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun nukeTable()

    companion object {
        const val TABLE_NAME = "todo"
    }
}