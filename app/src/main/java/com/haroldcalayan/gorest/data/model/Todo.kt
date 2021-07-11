package com.haroldcalayan.gorest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val description: String?
)