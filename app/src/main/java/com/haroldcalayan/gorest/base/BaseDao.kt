package com.haroldcalayan.gorest.base

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(vararg data: T)

    @Delete
    suspend fun delete(vararg data: T)

}
